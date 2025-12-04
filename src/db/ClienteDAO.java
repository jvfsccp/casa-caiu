package db;

import models.Cliente;
import java.sql.*;
import java.util.ArrayList;

public class ClienteDAO {
    private Connection connection;

    public ClienteDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    // CREATE
    public boolean inserir(Cliente cliente) {
        String sql = "INSERT INTO cliente (cpf, nome, telefone, email) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, cliente.getCpf());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEmail());
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    cliente.setIdCliente(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir cliente: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // READ
    public Cliente buscarPorId(int idCliente) {
        String sql = "SELECT * FROM cliente WHERE id_cliente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Cliente(
                    rs.getInt("id_cliente"),
                    rs.getString("cpf"),
                    rs.getString("nome"),
                    rs.getString("telefone"),
                    rs.getString("email")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar cliente: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Cliente> listarTodos() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente ORDER BY nome";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                clientes.add(new Cliente(
                    rs.getInt("id_cliente"),
                    rs.getString("cpf"),
                    rs.getString("nome"),
                    rs.getString("telefone"),
                    rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar clientes: " + e.getMessage());
            e.printStackTrace();
        }
        return clientes;
    }

    // UPDATE
    public boolean atualizar(Cliente cliente) {
        String sql = "UPDATE cliente SET cpf = ?, nome = ?, telefone = ?, email = ? WHERE id_cliente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cliente.getCpf());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEmail());
            stmt.setInt(5, cliente.getIdCliente());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar cliente: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // DELETE
    public boolean excluir(int idCliente) {
        String sql = "DELETE FROM cliente WHERE id_cliente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao excluir cliente: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // CONSULTAS ESPECIAIS
    public Cliente buscarPorCpf(String cpf) {
        String sql = "SELECT * FROM cliente WHERE cpf = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Cliente(
                    rs.getInt("id_cliente"),
                    rs.getString("cpf"),
                    rs.getString("nome"),
                    rs.getString("telefone"),
                    rs.getString("email")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar cliente por CPF: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Cliente> buscarPorNome(String nome) {
        ArrayList<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente WHERE nome LIKE ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                clientes.add(new Cliente(
                    rs.getInt("id_cliente"),
                    rs.getString("cpf"),
                    rs.getString("nome"),
                    rs.getString("telefone"),
                    rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar clientes por nome: " + e.getMessage());
            e.printStackTrace();
        }
        return clientes;
    }

    public Cliente buscarPorEmail(String email) {
        String sql = "SELECT * FROM cliente WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Cliente(
                    rs.getInt("id_cliente"),
                    rs.getString("cpf"),
                    rs.getString("nome"),
                    rs.getString("telefone"),
                    rs.getString("email")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar cliente por email: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public boolean cpfExiste(String cpf) {
        String sql = "SELECT COUNT(*) as total FROM cliente WHERE cpf = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("total") > 0;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao verificar CPF: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean emailExiste(String email) {
        String sql = "SELECT COUNT(*) as total FROM cliente WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("total") > 0;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao verificar email: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public int contarClientes() {
        String sql = "SELECT COUNT(*) as total FROM cliente";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao contar clientes: " + e.getMessage());
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
