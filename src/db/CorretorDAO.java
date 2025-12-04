package db;

import models.Corretor;
import java.sql.*;
import java.util.ArrayList;

public class CorretorDAO {
    private Connection connection;

    public CorretorDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    // CREATE - Inserir corretor
    public boolean inserir(Corretor corretor) {
        String sql = "INSERT INTO corretores (nome, telefone, email, creci) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, corretor.getNome());
            stmt.setString(2, corretor.getTelefone());
            stmt.setString(3, corretor.getEmail());
            stmt.setString(4, corretor.getCreci());
            
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    corretor.setId(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir corretor: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // READ - Buscar corretor por ID
    public Corretor buscarPorId(int id) {
        String sql = "SELECT * FROM corretores WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Corretor(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("creci")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar corretor por ID: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    // READ - Listar todos os corretores
    public ArrayList<Corretor> listarTodos() {
        ArrayList<Corretor> corretores = new ArrayList<>();
        String sql = "SELECT * FROM corretores";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Corretor corretor = new Corretor(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("creci")
                );
                corretores.add(corretor);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar corretores: " + e.getMessage());
            e.printStackTrace();
        }
        return corretores;
    }

    // UPDATE - Atualizar corretor
    public boolean atualizar(Corretor corretor) {
        String sql = "UPDATE corretores SET nome = ?, telefone = ?, email = ?, creci = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, corretor.getNome());
            stmt.setString(2, corretor.getTelefone());
            stmt.setString(3, corretor.getEmail());
            stmt.setString(4, corretor.getCreci());
            stmt.setInt(5, corretor.getId());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar corretor: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // DELETE - Excluir corretor
    public boolean excluir(int id) {
        String sql = "DELETE FROM corretores WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao excluir corretor: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // CONSULTA ESPECIAL - Buscar corretor por CRECI
    public Corretor buscarPorCreci(String creci) {
        String sql = "SELECT * FROM corretores WHERE creci = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, creci);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Corretor(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("creci")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar corretor por CRECI: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    // CONSULTA ESPECIAL - Buscar corretores por nome (busca parcial)
    public ArrayList<Corretor> buscarPorNome(String nome) {
        ArrayList<Corretor> corretores = new ArrayList<>();
        String sql = "SELECT * FROM corretores WHERE nome LIKE ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Corretor corretor = new Corretor(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("creci")
                );
                corretores.add(corretor);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar corretores por nome: " + e.getMessage());
            e.printStackTrace();
        }
        return corretores;
    }

    // CONSULTA ESPECIAL - Buscar corretor por email
    public Corretor buscarPorEmail(String email) {
        String sql = "SELECT * FROM corretores WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Corretor(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("creci")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar corretor por email: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    // CONSULTA ESPECIAL - Buscar corretores por telefone
    public ArrayList<Corretor> buscarPorTelefone(String telefone) {
        ArrayList<Corretor> corretores = new ArrayList<>();
        String sql = "SELECT * FROM corretores WHERE telefone LIKE ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + telefone + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Corretor corretor = new Corretor(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("creci")
                );
                corretores.add(corretor);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar corretores por telefone: " + e.getMessage());
            e.printStackTrace();
        }
        return corretores;
    }

    // CONSULTA ESPECIAL - Contar total de corretores
    public int contarCorretores() {
        String sql = "SELECT COUNT(*) as total FROM corretores";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao contar corretores: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    // CONSULTA ESPECIAL - Verificar se CRECI já existe
    public boolean creciExiste(String creci) {
        String sql = "SELECT COUNT(*) as total FROM corretores WHERE creci = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, creci);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("total") > 0;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao verificar CRECI: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // CONSULTA ESPECIAL - Verificar se email já existe
    public boolean emailExiste(String email) {
        String sql = "SELECT COUNT(*) as total FROM corretores WHERE email = ?";
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

    // CONSULTA ESPECIAL - Listar corretores ordenados por nome
    public ArrayList<Corretor> listarOrdenadosPorNome() {
        ArrayList<Corretor> corretores = new ArrayList<>();
        String sql = "SELECT * FROM corretores ORDER BY nome ASC";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Corretor corretor = new Corretor(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("creci")
                );
                corretores.add(corretor);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar corretores ordenados: " + e.getMessage());
            e.printStackTrace();
        }
        return corretores;
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
