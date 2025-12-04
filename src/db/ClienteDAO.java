package db;

import models.Cliente;
import java.sql.*;
import java.util.ArrayList;

public class ClienteDAO {
    private Connection connection;

    public ClienteDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    // CREATE - Inserir cliente
    public boolean inserir(Cliente cliente) {
        String sql = "INSERT INTO clientes (nome, telefone, email, cpf) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getCpf());
            
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    cliente.setId(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir cliente: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // READ - Buscar cliente por ID
    public Cliente buscarPorId(int id) {
        String sql = "SELECT * FROM clientes WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Cliente(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("cpf")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar cliente por ID: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    // READ - Listar todos os clientes
    public ArrayList<Cliente> listarTodos() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Cliente cliente = new Cliente(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("cpf")
                );
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar clientes: " + e.getMessage());
            e.printStackTrace();
        }
        return clientes;
    }

    // UPDATE - Atualizar cliente
    public boolean atualizar(Cliente cliente) {
        String sql = "UPDATE clientes SET nome = ?, telefone = ?, email = ?, cpf = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getCpf());
            stmt.setInt(5, cliente.getId());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar cliente: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // DELETE - Excluir cliente
    public boolean excluir(int id) {
        String sql = "DELETE FROM clientes WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao excluir cliente: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // CONSULTA ESPECIAL - Buscar cliente por CPF
    public Cliente buscarPorCpf(String cpf) {
        String sql = "SELECT * FROM clientes WHERE cpf = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Cliente(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("cpf")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar cliente por CPF: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    // CONSULTA ESPECIAL - Buscar clientes por nome (busca parcial)
    public ArrayList<Cliente> buscarPorNome(String nome) {
        ArrayList<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes WHERE nome LIKE ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Cliente cliente = new Cliente(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("cpf")
                );
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar clientes por nome: " + e.getMessage());
            e.printStackTrace();
        }
        return clientes;
    }

    // CONSULTA ESPECIAL - Buscar cliente por email
    public Cliente buscarPorEmail(String email) {
        String sql = "SELECT * FROM clientes WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Cliente(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("cpf")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar cliente por email: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    // CONSULTA ESPECIAL - Buscar clientes por telefone
    public ArrayList<Cliente> buscarPorTelefone(String telefone) {
        ArrayList<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes WHERE telefone LIKE ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + telefone + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Cliente cliente = new Cliente(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("cpf")
                );
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar clientes por telefone: " + e.getMessage());
            e.printStackTrace();
        }
        return clientes;
    }

    // CONSULTA ESPECIAL - Contar total de clientes
    public int contarClientes() {
        String sql = "SELECT COUNT(*) as total FROM clientes";
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

    // CONSULTA ESPECIAL - Verificar se CPF já existe
    public boolean cpfExiste(String cpf) {
        String sql = "SELECT COUNT(*) as total FROM clientes WHERE cpf = ?";
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

    // CONSULTA ESPECIAL - Verificar se email já existe
    public boolean emailExiste(String email) {
        String sql = "SELECT COUNT(*) as total FROM clientes WHERE email = ?";
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

    // Fechar conexão
    public void fecharConexao() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar conexão: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
