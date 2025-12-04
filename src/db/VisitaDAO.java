package db;

import models.Cliente;
import models.Corretor;
import models.Imovel;
import models.Visita;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VisitaDAO {

    public void create(Visita visita) {
        String sql = "INSERT INTO visita (imovel_id, cliente_id, corretor_id, data_hora) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, visita.getImovel().getId());
            pstmt.setInt(2, visita.getCliente().getId());
            pstmt.setInt(3, visita.getCorretor().getId());
            pstmt.setTimestamp(4, Timestamp.valueOf(visita.getDataHora()));

            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    visita.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Visita getById(int id) {
        String sql = "SELECT * FROM visita WHERE id = ?";
        Visita visita = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                ImovelDAO imovelDAO = new ImovelDAO();
                ClienteDAO clienteDAO = new ClienteDAO();
                CorretorDAO corretorDAO = new CorretorDAO();

                Imovel imovel = imovelDAO.buscarPorId(rs.getInt("imovel_id"));
                Cliente cliente = clienteDAO.buscarPorId(rs.getInt("cliente_id"));
                Corretor corretor = corretorDAO.buscarPorId(rs.getInt("corretor_id"));

                LocalDateTime dataHora = rs.getTimestamp("data_hora").toLocalDateTime();

                visita = new Visita(id, imovel, cliente, corretor, dataHora);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return visita;
    }

    public List<Visita> getAll() {
        String sql = "SELECT * FROM visita";
        List<Visita> visitas = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            ImovelDAO imovelDAO = new ImovelDAO();
            ClienteDAO clienteDAO = new ClienteDAO();
            CorretorDAO corretorDAO = new CorretorDAO();

            while (rs.next()) {
                Imovel imovel = imovelDAO.buscarPorId(rs.getInt("imovel_id"));
                Cliente cliente = clienteDAO.buscarPorId(rs.getInt("cliente_id"));
                Corretor corretor = corretorDAO.buscarPorId(rs.getInt("corretor_id"));

                LocalDateTime dataHora = rs.getTimestamp("data_hora").toLocalDateTime();

                Visita visita = new Visita(rs.getInt("id"), imovel, cliente, corretor, dataHora);
                visitas.add(visita);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return visitas;
    }

    public void update(Visita visita) {
        String sql = "UPDATE visita SET imovel_id = ?, cliente_id = ?, corretor_id = ?, data_hora = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, visita.getImovel().getId());
            pstmt.setInt(2, visita.getCliente().getId());
            pstmt.setInt(3, visita.getCorretor().getId());
            pstmt.setTimestamp(4, Timestamp.valueOf(visita.getDataHora()));
            pstmt.setInt(5, visita.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM visita WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
