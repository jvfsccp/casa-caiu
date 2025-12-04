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
        String sql = "INSERT INTO imovel (id_tipo, endereco, id_status) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, imovel.getIdTipo());
            stmt.setString(2, imovel.getEndereco());
            stmt.setInt(3, imovel.getIdStatus());
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    imovel.setIdImovel(rs.getInt(1));
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
    public Imovel buscarPorId(int idImovel) {
        String sql = "SELECT * FROM imovel WHERE id_imovel = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idImovel);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Imovel(
                    rs.getInt("id_imovel"),
                    rs.getInt("id_tipo"),
                    rs.getString("endereco"),
                    rs.getInt("id_status")
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
        String sql = "SELECT * FROM imovel";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                imoveis.add(new Imovel(
                    rs.getInt("id_imovel"),
                    rs.getInt("id_tipo"),
                    rs.getString("endereco"),
                    rs.getInt("id_status")
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
        String sql = "UPDATE imovel SET id_tipo = ?, endereco = ?, id_status = ? WHERE id_imovel = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, imovel.getIdTipo());
            stmt.setString(2, imovel.getEndereco());
            stmt.setInt(3, imovel.getIdStatus());
            stmt.setInt(4, imovel.getIdImovel());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar imóvel: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // DELETE
    public boolean excluir(int idImovel) {
        String sql = "DELETE FROM imovel WHERE id_imovel = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idImovel);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao excluir imóvel: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // CONSULTAS ESPECIAIS
    public ArrayList<Imovel> buscarPorTipo(int idTipo) {
        ArrayList<Imovel> imoveis = new ArrayList<>();
        String sql = "SELECT * FROM imovel WHERE id_tipo = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idTipo);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                imoveis.add(new Imovel(
                    rs.getInt("id_imovel"),
                    rs.getInt("id_tipo"),
                    rs.getString("endereco"),
                    rs.getInt("id_status")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar imóveis por tipo: " + e.getMessage());
            e.printStackTrace();
        }
        return imoveis;
    }

    public ArrayList<Imovel> buscarPorStatus(int idStatus) {
        ArrayList<Imovel> imoveis = new ArrayList<>();
        String sql = "SELECT * FROM imovel WHERE id_status = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idStatus);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                imoveis.add(new Imovel(
                    rs.getInt("id_imovel"),
                    rs.getInt("id_tipo"),
                    rs.getString("endereco"),
                    rs.getInt("id_status")
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
        String sql = "SELECT * FROM imovel WHERE endereco LIKE ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + endereco + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                imoveis.add(new Imovel(
                    rs.getInt("id_imovel"),
                    rs.getInt("id_tipo"),
                    rs.getString("endereco"),
                    rs.getInt("id_status")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar imóveis por endereço: " + e.getMessage());
            e.printStackTrace();
        }
        return imoveis;
    }

    public int contarImoveis() {
        String sql = "SELECT COUNT(*) as total FROM imovel";
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
