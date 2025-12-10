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

    public boolean inserir(Proposta proposta) {
        String sql = "INSERT INTO propostas (imovel_id, cliente_id, data_proposta) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, proposta.getImovelId());
            stmt.setInt(2, proposta.getClienteId());
            stmt.setDate(3, Date.valueOf(proposta.getDataProposta()));
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    proposta.setId(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
        }
        return false;
    }

    public Proposta buscarPorId(int id) {
        String sql = "SELECT * FROM propostas WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Proposta(
                    rs.getInt("id"),
                    rs.getInt("imovel_id"),
                    rs.getInt("cliente_id"),
                    rs.getDate("data_proposta").toLocalDate()
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar proposta: " + e.getMessage());
        }
        return null;
    }

    public ArrayList<Proposta> listarTodos() {
        ArrayList<Proposta> propostas = new ArrayList<>();
        String sql = "SELECT * FROM propostas ORDER BY data_proposta DESC";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                propostas.add(new Proposta(
                    rs.getInt("id"),
                    rs.getInt("imovel_id"),
                    rs.getInt("cliente_id"),
                    rs.getDate("data_proposta").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar propostas: " + e.getMessage());
        }
        return propostas;
    }

    public boolean atualizar(Proposta proposta) {
        String sql = "UPDATE propostas SET imovel_id = ?, cliente_id = ?, data_proposta = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, proposta.getImovelId());
            stmt.setInt(2, proposta.getClienteId());
            stmt.setDate(3, Date.valueOf(proposta.getDataProposta()));
            stmt.setInt(4, proposta.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar proposta: " + e.getMessage());
        }
        return false;
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM propostas WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao excluir proposta: " + e.getMessage());
        }
        return false;
    }

    public ArrayList<Proposta> buscarPorImovel(int imovelId) {
        ArrayList<Proposta> propostas = new ArrayList<>();
        String sql = "SELECT * FROM propostas WHERE imovel_id = ? ORDER BY data_proposta DESC";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, imovelId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                propostas.add(new Proposta(
                    rs.getInt("id"),
                    rs.getInt("imovel_id"),
                    rs.getInt("cliente_id"),
                    rs.getDate("data_proposta").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar propostas por imóvel: " + e.getMessage());
        }
        return propostas;
    }

    public ArrayList<Proposta> buscarPorCliente(int clienteId) {
        ArrayList<Proposta> propostas = new ArrayList<>();
        String sql = "SELECT * FROM propostas WHERE cliente_id = ? ORDER BY data_proposta DESC";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, clienteId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                propostas.add(new Proposta(
                    rs.getInt("id"),
                    rs.getInt("imovel_id"),
                    rs.getInt("cliente_id"),
                    rs.getDate("data_proposta").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar propostas por cliente: " + e.getMessage());
        }
        return propostas;
    }

    public ArrayList<Proposta> buscarPorData(LocalDate data) {
        ArrayList<Proposta> propostas = new ArrayList<>();
        String sql = "SELECT * FROM propostas WHERE data_proposta = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(data));
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                propostas.add(new Proposta(
                    rs.getInt("id"),
                    rs.getInt("imovel_id"),
                    rs.getInt("cliente_id"),
                    rs.getDate("data_proposta").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar propostas por data: " + e.getMessage());
        }
        return propostas;
    }

    public int contarPropostasPorImovel(int imovelId) {
        String sql = "SELECT COUNT(*) as total FROM propostas WHERE imovel_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, imovelId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao contar propostas: " + e.getMessage());
        }
        return 0;
    }

    public int contarPropostasPorCliente(int clienteId) {
        String sql = "SELECT COUNT(*) as total FROM propostas WHERE cliente_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, clienteId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao contar propostas: " + e.getMessage());
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
