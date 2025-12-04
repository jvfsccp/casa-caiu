package db;

import models.Visita;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class VisitaDAO {
    private Connection connection;

    public VisitaDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    // CREATE
    public boolean inserir(Visita visita) {
        String sql = "INSERT INTO visita (id_imovel, id_cliente, id_corretor, data_visita) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, visita.getIdImovel());
            stmt.setInt(2, visita.getIdCliente());
            stmt.setInt(3, visita.getIdCorretor());
            stmt.setDate(4, Date.valueOf(visita.getDataVisita()));
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    visita.setIdVisita(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir visita: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // READ
    public Visita buscarPorId(int idVisita) {
        String sql = "SELECT * FROM visita WHERE id_visita = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idVisita);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Visita(
                    rs.getInt("id_visita"),
                    rs.getInt("id_imovel"),
                    rs.getInt("id_cliente"),
                    rs.getInt("id_corretor"),
                    rs.getDate("data_visita").toLocalDate()
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar visita: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Visita> listarTodos() {
        ArrayList<Visita> visitas = new ArrayList<>();
        String sql = "SELECT * FROM visita ORDER BY data_visita DESC";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                visitas.add(new Visita(
                    rs.getInt("id_visita"),
                    rs.getInt("id_imovel"),
                    rs.getInt("id_cliente"),
                    rs.getInt("id_corretor"),
                    rs.getDate("data_visita").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar visitas: " + e.getMessage());
            e.printStackTrace();
        }
        return visitas;
    }

    // UPDATE
    public boolean atualizar(Visita visita) {
        String sql = "UPDATE visita SET id_imovel = ?, id_cliente = ?, id_corretor = ?, data_visita = ? WHERE id_visita = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, visita.getIdImovel());
            stmt.setInt(2, visita.getIdCliente());
            stmt.setInt(3, visita.getIdCorretor());
            stmt.setDate(4, Date.valueOf(visita.getDataVisita()));
            stmt.setInt(5, visita.getIdVisita());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar visita: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // DELETE
    public boolean excluir(int idVisita) {
        String sql = "DELETE FROM visita WHERE id_visita = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idVisita);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao excluir visita: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // CONSULTAS ESPECIAIS
    public ArrayList<Visita> buscarPorImovel(int idImovel) {
        ArrayList<Visita> visitas = new ArrayList<>();
        String sql = "SELECT * FROM visita WHERE id_imovel = ? ORDER BY data_visita DESC";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idImovel);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                visitas.add(new Visita(
                    rs.getInt("id_visita"),
                    rs.getInt("id_imovel"),
                    rs.getInt("id_cliente"),
                    rs.getInt("id_corretor"),
                    rs.getDate("data_visita").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar visitas por imóvel: " + e.getMessage());
            e.printStackTrace();
        }
        return visitas;
    }

    public ArrayList<Visita> buscarPorCliente(int idCliente) {
        ArrayList<Visita> visitas = new ArrayList<>();
        String sql = "SELECT * FROM visita WHERE id_cliente = ? ORDER BY data_visita DESC";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                visitas.add(new Visita(
                    rs.getInt("id_visita"),
                    rs.getInt("id_imovel"),
                    rs.getInt("id_cliente"),
                    rs.getInt("id_corretor"),
                    rs.getDate("data_visita").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar visitas por cliente: " + e.getMessage());
            e.printStackTrace();
        }
        return visitas;
    }

    public ArrayList<Visita> buscarPorCorretor(int idCorretor) {
        ArrayList<Visita> visitas = new ArrayList<>();
        String sql = "SELECT * FROM visita WHERE id_corretor = ? ORDER BY data_visita DESC";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCorretor);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                visitas.add(new Visita(
                    rs.getInt("id_visita"),
                    rs.getInt("id_imovel"),
                    rs.getInt("id_cliente"),
                    rs.getInt("id_corretor"),
                    rs.getDate("data_visita").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar visitas por corretor: " + e.getMessage());
            e.printStackTrace();
        }
        return visitas;
    }

    public ArrayList<Visita> buscarPorData(LocalDate data) {
        ArrayList<Visita> visitas = new ArrayList<>();
        String sql = "SELECT * FROM visita WHERE data_visita = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(data));
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                visitas.add(new Visita(
                    rs.getInt("id_visita"),
                    rs.getInt("id_imovel"),
                    rs.getInt("id_cliente"),
                    rs.getInt("id_corretor"),
                    rs.getDate("data_visita").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar visitas por data: " + e.getMessage());
            e.printStackTrace();
        }
        return visitas;
    }

    public ArrayList<String> listarComDetalhes() {
        ArrayList<String> detalhes = new ArrayList<>();
        String sql = "SELECT v.id_visita, v.data_visita, " +
                    "i.endereco, t.descricao as tipo_imovel, " +
                    "c.nome as nome_cliente, c.telefone as telefone_cliente, " +
                    "cor.nome as nome_corretor, cor.telefone as telefone_corretor " +
                    "FROM visita v " +
                    "INNER JOIN imovel i ON v.id_imovel = i.id_imovel " +
                    "INNER JOIN tipo_imovel t ON i.id_tipo = t.id_tipo " +
                    "INNER JOIN cliente c ON v.id_cliente = c.id_cliente " +
                    "INNER JOIN corretor cor ON v.id_corretor = cor.id_corretor " +
                    "ORDER BY v.data_visita DESC";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                String detalhe = String.format(
                    "Visita #%d | Data: %s | Imóvel: %s (%s) | Cliente: %s (%s) | Corretor: %s (%s)",
                    rs.getInt("id_visita"),
                    rs.getDate("data_visita").toLocalDate(),
                    rs.getString("endereco"),
                    rs.getString("tipo_imovel"),
                    rs.getString("nome_cliente"),
                    rs.getString("telefone_cliente"),
                    rs.getString("nome_corretor"),
                    rs.getString("telefone_corretor")
                );
                detalhes.add(detalhe);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar visitas com detalhes: " + e.getMessage());
            e.printStackTrace();
        }
        return detalhes;
    }

    public int contarVisitasPorImovel(int idImovel) {
        String sql = "SELECT COUNT(*) as total FROM visita WHERE id_imovel = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idImovel);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao contar visitas: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    public int contarVisitasPorCorretor(int idCorretor) {
        String sql = "SELECT COUNT(*) as total FROM visita WHERE id_corretor = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCorretor);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao contar visitas: " + e.getMessage());
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
