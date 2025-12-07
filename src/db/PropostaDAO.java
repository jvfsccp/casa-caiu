package db;

import models.Proposta;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class PropostaDAO {
    private Connection connection;

    public PropostaDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    // CREATE
    public boolean inserir(Proposta proposta) {
        String sql = "INSERT INTO propostas (imovel_id, cliente_id, data_proposta) VALUES (?, ?, ?)"; // PADRONIZADO
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, proposta.getImovelId()); // PADRONIZADO
            stmt.setInt(2, proposta.getClienteId()); // PADRONIZADO
            stmt.setDate(3, Date.valueOf(proposta.getDataProposta()));
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    proposta.setId(rs.getInt(1)); // PADRONIZADO
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir proposta: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // READ
    public Proposta buscarPorId(int id) { // PADRONIZADO
        String sql = "SELECT * FROM propostas WHERE id = ?"; // PADRONIZADO
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Proposta(
                    rs.getInt("id"), // PADRONIZADO
                    rs.getInt("imovel_id"), // PADRONIZADO
                    rs.getInt("cliente_id"), // PADRONIZADO
                    rs.getDate("data_proposta").toLocalDate()
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar proposta: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Proposta> listarTodos() {
        ArrayList<Proposta> propostas = new ArrayList<>();
        String sql = "SELECT * FROM propostas ORDER BY data_proposta DESC"; // PADRONIZADO
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                propostas.add(new Proposta(
                    rs.getInt("id"), // PADRONIZADO
                    rs.getInt("imovel_id"), // PADRONIZADO
                    rs.getInt("cliente_id"), // PADRONIZADO
                    rs.getDate("data_proposta").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar propostas: " + e.getMessage());
            e.printStackTrace();
        }
        return propostas;
    }

    // UPDATE
    public boolean atualizar(Proposta proposta) {
        String sql = "UPDATE propostas SET imovel_id = ?, cliente_id = ?, data_proposta = ? WHERE id = ?"; // PADRONIZADO
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, proposta.getImovelId()); // PADRONIZADO
            stmt.setInt(2, proposta.getClienteId()); // PADRONIZADO
            stmt.setDate(3, Date.valueOf(proposta.getDataProposta()));
            stmt.setInt(4, proposta.getId()); // PADRONIZADO
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar proposta: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // DELETE
    public boolean excluir(int id) { // PADRONIZADO
        String sql = "DELETE FROM propostas WHERE id = ?"; // PADRONIZADO
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao excluir proposta: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // CONSULTAS ESPECIAIS
    public ArrayList<Proposta> buscarPorImovel(int imovelId) { // PADRONIZADO
        ArrayList<Proposta> propostas = new ArrayList<>();
        String sql = "SELECT * FROM propostas WHERE imovel_id = ? ORDER BY data_proposta DESC"; // PADRONIZADO
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, imovelId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                propostas.add(new Proposta(
                    rs.getInt("id"), // PADRONIZADO
                    rs.getInt("imovel_id"), // PADRONIZADO
                    rs.getInt("cliente_id"), // PADRONIZADO
                    rs.getDate("data_proposta").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar propostas por imóvel: " + e.getMessage());
            e.printStackTrace();
        }
        return propostas;
    }

    public ArrayList<Proposta> buscarPorCliente(int clienteId) { // PADRONIZADO
        ArrayList<Proposta> propostas = new ArrayList<>();
        String sql = "SELECT * FROM propostas WHERE cliente_id = ? ORDER BY data_proposta DESC"; // PADRONIZADO
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, clienteId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                propostas.add(new Proposta(
                    rs.getInt("id"), // PADRONIZADO
                    rs.getInt("imovel_id"), // PADRONIZADO
                    rs.getInt("cliente_id"), // PADRONIZADO
                    rs.getDate("data_proposta").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar propostas por cliente: " + e.getMessage());
            e.printStackTrace();
        }
        return propostas;
    }

    public ArrayList<Proposta> buscarPorData(LocalDate data) {
        ArrayList<Proposta> propostas = new ArrayList<>();
        String sql = "SELECT * FROM propostas WHERE data_proposta = ?"; // PADRONIZADO
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(data));
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                propostas.add(new Proposta(
                    rs.getInt("id"), // PADRONIZADO
                    rs.getInt("imovel_id"), // PADRONIZADO
                    rs.getInt("cliente_id"), // PADRONIZADO
                    rs.getDate("data_proposta").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar propostas por data: " + e.getMessage());
            e.printStackTrace();
        }
        return propostas;
    }

    public int contarPropostasPorImovel(int imovelId) { // PADRONIZADO
        String sql = "SELECT COUNT(*) as total FROM propostas WHERE imovel_id = ?"; // PADRONIZADO
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, imovelId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao contar propostas: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    public int contarPropostasPorCliente(int clienteId) { // PADRONIZADO
        String sql = "SELECT COUNT(*) as total FROM propostas WHERE cliente_id = ?"; // PADRONIZADO
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, clienteId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao contar propostas: " + e.getMessage());
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
