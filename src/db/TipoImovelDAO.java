package db;

import models.TipoImovel;
import java.sql.*;
import java.util.ArrayList;

public class TipoImovelDAO {
    private Connection connection;

    public TipoImovelDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    // CREATE
    public boolean inserir(TipoImovel tipo) {
        String sql = "INSERT INTO tipo_imoveis (descricao) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, tipo.getDescricao());
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    tipo.setId(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir tipo de imóvel: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // READ
    public TipoImovel buscarPorId(int id) {
        String sql = "SELECT * FROM tipo_imoveis WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new TipoImovel(
                    rs.getInt("id"),
                    rs.getString("descricao")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar tipo de imóvel: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<TipoImovel> listarTodos() {
        ArrayList<TipoImovel> tipos = new ArrayList<>();
        String sql = "SELECT * FROM tipo_imoveis ORDER BY id"; // CORRIGIDO
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                tipos.add(new TipoImovel(
                    rs.getInt("id"),
                    rs.getString("descricao")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar tipos de imóvel: " + e.getMessage());
            e.printStackTrace();
        }
        return tipos;
    }

    // UPDATE
    public boolean atualizar(TipoImovel tipo) {
        String sql = "UPDATE tipo_imoveis SET descricao = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, tipo.getDescricao());
            stmt.setInt(2, tipo.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar tipo de imóvel: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // DELETE
    public boolean excluir(int id) {
        String sql = "DELETE FROM tipo_imoveis WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao excluir tipo de imóvel: " + e.getMessage());
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
