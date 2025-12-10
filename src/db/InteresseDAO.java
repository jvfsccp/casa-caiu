package db;

import models.Interesse;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class InteresseDAO {
    private Connection connection;

    public InteresseDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    public boolean inserir(Interesse interesse) {
        String sql = "INSERT INTO interesses (cliente_id, imovel_id, data_interesse) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, interesse.getClienteId());
            stmt.setInt(2, interesse.getImovelId());
            stmt.setDate(3, Date.valueOf(interesse.getDataInteresse()));

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        interesse.setId(rs.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                System.err.println("Erro: Este cliente já demonstrou interesse neste imóvel.");
            } else {
                System.err.println("Erro ao inserir interesse: " + e.getMessage());
            }
        }
        return false;
    }

    public ArrayList<Interesse> listarTodos() {
        ArrayList<Interesse> interesses = new ArrayList<>();
        String sql = "SELECT * FROM interesses ORDER BY data_interesse DESC";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                interesses.add(mapRowToInteresse(rs));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar interesses: " + e.getMessage());
        }
        return interesses;
    }

    public ArrayList<Interesse> buscarPorCliente(int clienteId) {
        ArrayList<Interesse> interesses = new ArrayList<>();
        String sql = "SELECT * FROM interesses WHERE cliente_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, clienteId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    interesses.add(mapRowToInteresse(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar interesses por cliente: " + e.getMessage());
        }
        return interesses;
    }
    
    public ArrayList<Interesse> buscarPorImovel(int imovelId) {
        ArrayList<Interesse> interesses = new ArrayList<>();
        String sql = "SELECT * FROM interesses WHERE imovel_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, imovelId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    interesses.add(mapRowToInteresse(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar interesses por imóvel: " + e.getMessage());
        }
        return interesses;
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM interesses WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao excluir interesse: " + e.getMessage());
        }
        return false;
    }

    private Interesse mapRowToInteresse(ResultSet rs) throws SQLException {
        return new Interesse(
            rs.getInt("id"),
            rs.getInt("cliente_id"),
            rs.getInt("imovel_id"),
            rs.getDate("data_interesse").toLocalDate()
        );
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
