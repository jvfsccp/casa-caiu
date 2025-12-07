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
        String sql = "INSERT INTO visitas (imovel_id, cliente_id, corretor_id, data_visita) VALUES (?, ?, ?, ?)"; // PADRONIZADO
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, visita.getImovelId()); // PADRONIZADO
            stmt.setInt(2, visita.getClienteId()); // PADRONIZADO
            stmt.setInt(3, visita.getCorretorId()); // PADRONIZADO
            stmt.setDate(4, Date.valueOf(visita.getDataVisita()));
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    visita.setId(rs.getInt(1)); // PADRONIZADO
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
    public Visita buscarPorId(int id) { // PADRONIZADO
        String sql = "SELECT * FROM visitas WHERE id = ?"; // PADRONIZADO
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Visita(
                    rs.getInt("id"), // PADRONIZADO
                    rs.getInt("imovel_id"), // PADRONIZADO
                    rs.getInt("cliente_id"), // PADRONIZADO
                    rs.getInt("corretor_id"), // PADRONIZADO
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
        String sql = "SELECT * FROM visitas ORDER BY data_visita DESC"; // PADRONIZADO
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                visitas.add(new Visita(
                    rs.getInt("id"), // PADRONIZADO
                    rs.getInt("imovel_id"), // PADRONIZADO
                    rs.getInt("cliente_id"), // PADRONIZADO
                    rs.getInt("corretor_id"), // PADRONIZADO
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
        String sql = "UPDATE visitas SET imovel_id = ?, cliente_id = ?, corretor_id = ?, data_visita = ? WHERE id = ?"; // PADRONIZADO
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, visita.getImovelId()); // PADRONIZADO
            stmt.setInt(2, visita.getClienteId()); // PADRONIZADO
            stmt.setInt(3, visita.getCorretorId()); // PADRONIZADO
            stmt.setDate(4, Date.valueOf(visita.getDataVisita()));
            stmt.setInt(5, visita.getId()); // PADRONIZADO
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar visita: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // DELETE
    public boolean excluir(int id) { // PADRONIZADO
        String sql = "DELETE FROM visitas WHERE id = ?"; // PADRONIZADO
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao excluir visita: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // CONSULTAS ESPECIAIS
    public ArrayList<Visita> buscarPorImovel(int imovelId) { // PADRONIZADO
        ArrayList<Visita> visitas = new ArrayList<>();
        String sql = "SELECT * FROM visitas WHERE imovel_id = ? ORDER BY data_visita DESC"; // PADRONIZADO
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, imovelId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                visitas.add(new Visita(
                    rs.getInt("id"), // PADRONIZADO
                    rs.getInt("imovel_id"), // PADRONIZADO
                    rs.getInt("cliente_id"), // PADRONIZADO
                    rs.getInt("corretor_id"), // PADRONIZADO
                    rs.getDate("data_visita").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar visitas por imóvel: " + e.getMessage());
            e.printStackTrace();
        }
        return visitas;
    }

    public ArrayList<Visita> buscarPorCliente(int clienteId) { // PADRONIZADO
        ArrayList<Visita> visitas = new ArrayList<>();
        String sql = "SELECT * FROM visitas WHERE cliente_id = ? ORDER BY data_visita DESC"; // PADRONIZADO
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, clienteId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                visitas.add(new Visita(
                    rs.getInt("id"), // PADRONIZADO
                    rs.getInt("imovel_id"), // PADRONIZADO
                    rs.getInt("cliente_id"), // PADRONIZADO
                    rs.getInt("corretor_id"), // PADRONIZADO
                    rs.getDate("data_visita").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar visitas por cliente: " + e.getMessage());
            e.printStackTrace();
        }
        return visitas;
    }

    public ArrayList<Visita> buscarPorCorretor(int corretorId) { // PADRONIZADO
        ArrayList<Visita> visitas = new ArrayList<>();
        String sql = "SELECT * FROM visitas WHERE corretor_id = ? ORDER BY data_visita DESC"; // PADRONIZADO
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, corretorId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                visitas.add(new Visita(
                    rs.getInt("id"), // PADRONIZADO
                    rs.getInt("imovel_id"), // PADRONIZADO
                    rs.getInt("cliente_id"), // PADRONIZADO
                    rs.getInt("corretor_id"), // PADRONIZADO
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
        String sql = "SELECT * FROM visitas WHERE data_visita = ?"; // PADRONIZADO
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(data));
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                visitas.add(new Visita(
                    rs.getInt("id"), // PADRONIZADO
                    rs.getInt("imovel_id"), // PADRONIZADO
                    rs.getInt("cliente_id"), // PADRONIZADO
                    rs.getInt("corretor_id"), // PADRONIZADO
                    rs.getDate("data_visita").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar visitas por data: " + e.getMessage());
            e.printStackTrace();
        }
        return visitas;
    }

    public int contarVisitasPorImovel(int imovelId) { // PADRONIZADO
        String sql = "SELECT COUNT(*) as total FROM visitas WHERE imovel_id = ?"; // PADRONIZADO
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, imovelId);
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

    public int contarVisitasPorCorretor(int corretorId) { // PADRONIZADO
        String sql = "SELECT COUNT(*) as total FROM visitas WHERE corretor_id = ?"; // PADRONIZADO
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, corretorId);
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
