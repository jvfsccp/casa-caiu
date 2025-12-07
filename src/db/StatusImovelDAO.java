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
        String sql = "INSERT INTO status_imoveis (descricao) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, status.getDescricao());
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    status.setId(rs.getInt(1));
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
    public StatusImovel buscarPorId(int id) {
        String sql = "SELECT * FROM status_imoveis WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new StatusImovel(
                    rs.getInt("id"),
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
        String sql = "SELECT * FROM status_imoveis ORDER BY id"; // CORRIGIDO
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                statuses.add(new StatusImovel(
                    rs.getInt("id"),
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
        String sql = "UPDATE status_imoveis SET descricao = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, status.getDescricao());
            stmt.setInt(2, status.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar status de imóvel: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // DELETE
    public boolean excluir(int id) {
        String sql = "DELETE FROM status_imoveis WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
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
