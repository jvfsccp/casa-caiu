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
        String sql = "INSERT INTO proposta (id_imovel, id_cliente, data_proposta) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, proposta.getIdImovel());
            stmt.setInt(2, proposta.getIdCliente());
            stmt.setDate(3, Date.valueOf(proposta.getDataProposta()));
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    proposta.setIdProposta(rs.getInt(1));
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
    public Proposta buscarPorId(int idProposta) {
        String sql = "SELECT * FROM proposta WHERE id_proposta = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idProposta);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Proposta(
                    rs.getInt("id_proposta"),
                    rs.getInt("id_imovel"),
                    rs.getInt("id_cliente"),
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
        String sql = "SELECT * FROM proposta ORDER BY data_proposta DESC";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                propostas.add(new Proposta(
                    rs.getInt("id_proposta"),
                    rs.getInt("id_imovel"),
                    rs.getInt("id_cliente"),
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
        String sql = "UPDATE proposta SET id_imovel = ?, id_cliente = ?, data_proposta = ? WHERE id_proposta = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, proposta.getIdImovel());
            stmt.setInt(2, proposta.getIdCliente());
            stmt.setDate(3, Date.valueOf(proposta.getDataProposta()));
            stmt.setInt(4, proposta.getIdProposta());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar proposta: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // DELETE
    public boolean excluir(int idProposta) {
        String sql = "DELETE FROM proposta WHERE id_proposta = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idProposta);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao excluir proposta: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // CONSULTAS ESPECIAIS
    public ArrayList<Proposta> buscarPorImovel(int idImovel) {
        ArrayList<Proposta> propostas = new ArrayList<>();
        String sql = "SELECT * FROM proposta WHERE id_imovel = ? ORDER BY data_proposta DESC";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idImovel);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                propostas.add(new Proposta(
                    rs.getInt("id_proposta"),
                    rs.getInt("id_imovel"),
                    rs.getInt("id_cliente"),
                    rs.getDate("data_proposta").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar propostas por imóvel: " + e.getMessage());
            e.printStackTrace();
        }
        return propostas;
    }

    public ArrayList<Proposta> buscarPorCliente(int idCliente) {
        ArrayList<Proposta> propostas = new ArrayList<>();
        String sql = "SELECT * FROM proposta WHERE id_cliente = ? ORDER BY data_proposta DESC";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                propostas.add(new Proposta(
                    rs.getInt("id_proposta"),
                    rs.getInt("id_imovel"),
                    rs.getInt("id_cliente"),
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
        String sql = "SELECT * FROM proposta WHERE data_proposta = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(data));
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                propostas.add(new Proposta(
                    rs.getInt("id_proposta"),
                    rs.getInt("id_imovel"),
                    rs.getInt("id_cliente"),
                    rs.getDate("data_proposta").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar propostas por data: " + e.getMessage());
            e.printStackTrace();
        }
        return propostas;
    }

    public ArrayList<String> listarComDetalhes() {
        ArrayList<String> detalhes = new ArrayList<>();
        String sql = "SELECT p.id_proposta, p.data_proposta, " +
                    "i.endereco, t.descricao as tipo_imovel, " +
                    "c.nome as nome_cliente, c.telefone as telefone_cliente " +
                    "FROM proposta p " +
                    "INNER JOIN imovel i ON p.id_imovel = i.id_imovel " +
                    "INNER JOIN tipo_imovel t ON i.id_tipo = t.id_tipo " +
                    "INNER JOIN cliente c ON p.id_cliente = c.id_cliente " +
                    "ORDER BY p.data_proposta DESC";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                String detalhe = String.format(
                    "Proposta #%d | Data: %s | Imóvel: %s (%s) | Cliente: %s (%s)",
                    rs.getInt("id_proposta"),
                    rs.getDate("data_proposta").toLocalDate(),
                    rs.getString("endereco"),
                    rs.getString("tipo_imovel"),
                    rs.getString("nome_cliente"),
                    rs.getString("telefone_cliente")
                );
                detalhes.add(detalhe);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar propostas com detalhes: " + e.getMessage());
            e.printStackTrace();
        }
        return detalhes;
    }

    public int contarPropostasPorImovel(int idImovel) {
        String sql = "SELECT COUNT(*) as total FROM proposta WHERE id_imovel = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idImovel);
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

    public int contarPropostasPorCliente(int idCliente) {
        String sql = "SELECT COUNT(*) as total FROM proposta WHERE id_cliente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
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
