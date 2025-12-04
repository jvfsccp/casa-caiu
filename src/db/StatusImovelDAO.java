package db;

import models.StatusImovel;
import java.sql.*;
import java.util.ArrayList;

public class StatusImovelDAO {
    private Connection connection;

    public StatusImovelDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    // CREATE
    public boolean inserir(StatusImovel status) {
        String sql = "INSERT INTO status_imovel (descricao) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, status.getDescricao());
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    status.setIdStatus(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir status de imóvel: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // READ
    public StatusImovel buscarPorId(int idStatus) {
        String sql = "SELECT * FROM status_imovel WHERE id_status = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idStatus);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new StatusImovel(
                    rs.getInt("id_status"),
                    rs.getString("descricao")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar status de imóvel: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<StatusImovel> listarTodos() {
        ArrayList<StatusImovel> statuses = new ArrayList<>();
        String sql = "SELECT * FROM status_imovel ORDER BY descricao";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                statuses.add(new StatusImovel(
                    rs.getInt("id_status"),
                    rs.getString("descricao")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar status de imóvel: " + e.getMessage());
            e.printStackTrace();
        }
        return statuses;
    }

    // UPDATE
    public boolean atualizar(StatusImovel status) {
        String sql = "UPDATE status_imovel SET descricao = ? WHERE id_status = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, status.getDescricao());
            stmt.setInt(2, status.getIdStatus());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar status de imóvel: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // DELETE
    public boolean excluir(int idStatus) {
        String sql = "DELETE FROM status_imovel WHERE id_status = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idStatus);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao excluir status de imóvel: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public void fecharConexao() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar conexão: " + e.getMessage());
        }
    }
}
