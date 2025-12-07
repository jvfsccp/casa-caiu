package db;

import models.Imovel;
import java.sql.*;
import java.util.ArrayList;

public class ImovelDAO {
    private Connection connection;

    public ImovelDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    // CREATE
    public boolean inserir(Imovel imovel) {
        String sql = "INSERT INTO imoveis (tipo_imovel_id, endereco, status_imovel_id) VALUES (?, ?, ?)"; // PADRONIZADO
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, imovel.getTipoImovelId()); // PADRONIZADO
            stmt.setString(2, imovel.getEndereco());
            stmt.setInt(3, imovel.getStatusImovelId()); // PADRONIZADO
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    imovel.setId(rs.getInt(1)); // PADRONIZADO
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir imóvel: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // READ
    public Imovel buscarPorId(int id) { // PADRONIZADO
        String sql = "SELECT * FROM imoveis WHERE id = ?"; // PADRONIZADO
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Imovel(
                    rs.getInt("id"), // PADRONIZADO
                    rs.getInt("tipo_imovel_id"), // PADRONIZADO
                    rs.getString("endereco"),
                    rs.getInt("status_imovel_id") // PADRONIZADO
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar imóvel: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Imovel> listarTodos() {
        ArrayList<Imovel> imoveis = new ArrayList<>();
        String sql = "SELECT * FROM imoveis"; // PADRONIZADO
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                imoveis.add(new Imovel(
                    rs.getInt("id"), // PADRONIZADO
                    rs.getInt("tipo_imovel_id"), // PADRONIZADO
                    rs.getString("endereco"),
                    rs.getInt("status_imovel_id") // PADRONIZADO
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar imóveis: " + e.getMessage());
            e.printStackTrace();
        }
        return imoveis;
    }

    // UPDATE
    public boolean atualizar(Imovel imovel) {
        String sql = "UPDATE imoveis SET tipo_imovel_id = ?, endereco = ?, status_imovel_id = ? WHERE id = ?"; // PADRONIZADO
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, imovel.getTipoImovelId()); // PADRONIZADO
            stmt.setString(2, imovel.getEndereco());
            stmt.setInt(3, imovel.getStatusImovelId()); // PADRONIZADO
            stmt.setInt(4, imovel.getId()); // PADRONIZADO
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar imóvel: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // DELETE
    public boolean excluir(int id) { // PADRONIZADO
        String sql = "DELETE FROM imoveis WHERE id = ?"; // PADRONIZADO
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao excluir imóvel: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // CONSULTAS ESPECIAIS
    public ArrayList<Imovel> buscarPorTipo(int tipoImovelId) { // PADRONIZADO
        ArrayList<Imovel> imoveis = new ArrayList<>();
        String sql = "SELECT * FROM imoveis WHERE tipo_imovel_id = ?"; // PADRONIZADO
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, tipoImovelId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                imoveis.add(new Imovel(
                    rs.getInt("id"), // PADRONIZADO
                    rs.getInt("tipo_imovel_id"), // PADRONIZADO
                    rs.getString("endereco"),
                    rs.getInt("status_imovel_id") // PADRONIZADO
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar imóveis por tipo: " + e.getMessage());
            e.printStackTrace();
        }
        return imoveis;
    }

    public ArrayList<Imovel> buscarPorStatus(int statusImovelId) { // PADRONIZADO
        ArrayList<Imovel> imoveis = new ArrayList<>();
        String sql = "SELECT * FROM imoveis WHERE status_imovel_id = ?"; // PADRONIZADO
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, statusImovelId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                imoveis.add(new Imovel(
                    rs.getInt("id"), // PADRONIZADO
                    rs.getInt("tipo_imovel_id"), // PADRONIZADO
                    rs.getString("endereco"),
                    rs.getInt("status_imovel_id") // PADRONIZADO
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar imóveis por status: " + e.getMessage());
            e.printStackTrace();
        }
        return imoveis;
    }

    public ArrayList<Imovel> buscarPorEndereco(String endereco) {
        ArrayList<Imovel> imoveis = new ArrayList<>();
        String sql = "SELECT * FROM imoveis WHERE endereco LIKE ?"; // PADRONIZADO
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + endereco + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                imoveis.add(new Imovel(
                    rs.getInt("id"), // PADRONIZADO
                    rs.getInt("tipo_imovel_id"), // PADRONIZADO
                    rs.getString("endereco"),
                    rs.getInt("status_imovel_id") // PADRONIZADO
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar imóveis por endereço: " + e.getMessage());
            e.printStackTrace();
        }
        return imoveis;
    }

    public int contarImoveis() {
        String sql = "SELECT COUNT(*) as total FROM imoveis"; // PADRONIZADO
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao contar imóveis: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
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
