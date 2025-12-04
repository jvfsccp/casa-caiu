package db;

import models.Cliente;
import models.Imovel;
import models.Proposta;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PropostaDAO {

    public void create(Proposta proposta) {
        String sql = "INSERT INTO proposta (imovel_id, cliente_id, valor_proposto, data_proposta, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, proposta.getImovel().getId());
            pstmt.setInt(2, proposta.getCliente().getId());
            pstmt.setDouble(3, proposta.getValorProposto());
            pstmt.setDate(4, Date.valueOf(proposta.getDataProposta()));
            pstmt.setString(5, proposta.getStatus());

            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    proposta.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Proposta getById(int id) {
        String sql = "SELECT * FROM proposta WHERE id = ?";
        Proposta proposta = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                proposta = mapRowToProposta(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proposta;
    }

    public List<Proposta> getAll() {
        String sql = "SELECT * FROM proposta";
        List<Proposta> propostas = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                propostas.add(mapRowToProposta(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return propostas;
    }

    public void update(Proposta proposta) {
        String sql = "UPDATE proposta SET imovel_id = ?, cliente_id = ?, valor_proposto = ?, data_proposta = ?, status = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, proposta.getImovel().getId());
            pstmt.setInt(2, proposta.getCliente().getId());
            pstmt.setDouble(3, proposta.getValorProposto());
            pstmt.setDate(4, Date.valueOf(proposta.getDataProposta()));
            pstmt.setString(5, proposta.getStatus());
            pstmt.setInt(6, proposta.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM proposta WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Proposta> getByCliente(int clienteId) {
        String sql = "SELECT * FROM proposta WHERE cliente_id = ?";
        return getPropostasByQuery(sql, clienteId);
    }

    public List<Proposta> getByImovel(int imovelId) {
        String sql = "SELECT * FROM proposta WHERE imovel_id = ?";
        return getPropostasByQuery(sql, imovelId);
    }

    public List<Proposta> getByStatus(String status) {
        String sql = "SELECT * FROM proposta WHERE status = ?";
        List<Proposta> propostas = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, status);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    propostas.add(mapRowToProposta(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return propostas;
    }

    private List<Proposta> getPropostasByQuery(String sql, int id) {
        List<Proposta> propostas = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    propostas.add(mapRowToProposta(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return propostas;
    }

    private Proposta mapRowToProposta(ResultSet rs) throws SQLException {
        ImovelDAO imovelDAO = new ImovelDAO();
        ClienteDAO clienteDAO = new ClienteDAO();

        Imovel imovel = imovelDAO.buscarPorId(rs.getInt("imovel_id"));
        Cliente cliente = clienteDAO.buscarPorId(rs.getInt("cliente_id"));

        return new Proposta(
                rs.getInt("id"),
                imovel,
                cliente,
                rs.getDouble("valor_proposto"),
                rs.getDate("data_proposta").toLocalDate(),
                rs.getString("status")
        );
    }
}
