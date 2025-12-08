package db;

import models.Corretor;
import java.sql.*;
import java.util.ArrayList;

public class CorretorDAO {
    private Connection connection;

    public CorretorDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    public boolean inserir(Corretor corretor) {
        String sql = "INSERT INTO corretores (cpf, nome, creci, telefone, email) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, corretor.getCpf());
            stmt.setString(2, corretor.getNome());
            stmt.setString(3, corretor.getCreci());
            stmt.setString(4, corretor.getTelefone());
            stmt.setString(5, corretor.getEmail());
            
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

    public Corretor buscarPorId(int id) {
        String sql = "SELECT * FROM corretores WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Corretor(
                    rs.getInt("id"),
                    rs.getString("cpf"),
                    rs.getString("nome"),
                    rs.getString("creci"),
                    rs.getString("telefone"),
                    rs.getString("email")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar corretor: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Corretor> listarTodos() {
        ArrayList<Corretor> corretores = new ArrayList<>();
        String sql = "SELECT * FROM corretores ORDER BY nome";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                corretores.add(new Corretor(
                    rs.getInt("id"),
                    rs.getString("cpf"),
                    rs.getString("nome"),
                    rs.getString("creci"),
                    rs.getString("telefone"),
                    rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar corretores: " + e.getMessage());
            e.printStackTrace();
        }
        return corretores;
    }

    public boolean atualizar(Corretor corretor) {
        String sql = "UPDATE corretores SET cpf = ?, nome = ?, creci = ?, telefone = ?, email = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, corretor.getCpf());
            stmt.setString(2, corretor.getNome());
            stmt.setString(3, corretor.getCreci());
            stmt.setString(4, corretor.getTelefone());
            stmt.setString(5, corretor.getEmail());
            stmt.setInt(6, corretor.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar corretor: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

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

    public Corretor buscarPorCpf(String cpf) {
        String sql = "SELECT * FROM corretores WHERE cpf = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Corretor(
                    rs.getInt("id"),
                    rs.getString("cpf"),
                    rs.getString("nome"),
                    rs.getString("creci"),
                    rs.getString("telefone"),
                    rs.getString("email")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar corretor por CPF: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public Corretor buscarPorCreci(String creci) {
        String sql = "SELECT * FROM corretores WHERE creci = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, creci);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Corretor(
                    rs.getInt("id"),
                    rs.getString("cpf"),
                    rs.getString("nome"),
                    rs.getString("creci"),
                    rs.getString("telefone"),
                    rs.getString("email")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar corretor por CRECI: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Corretor> buscarPorNome(String nome) {
        ArrayList<Corretor> corretores = new ArrayList<>();
        String sql = "SELECT * FROM corretores WHERE nome LIKE ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                corretores.add(new Corretor(
                    rs.getInt("id"),
                    rs.getString("cpf"),
                    rs.getString("nome"),
                    rs.getString("creci"),
                    rs.getString("telefone"),
                    rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar corretores por nome: " + e.getMessage());
            e.printStackTrace();
        }
        return corretores;
    }

    public Corretor buscarPorEmail(String email) {
        String sql = "SELECT * FROM corretores WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Corretor(
                    rs.getInt("id"),
                    rs.getString("cpf"),
                    rs.getString("nome"),
                    rs.getString("creci"),
                    rs.getString("telefone"),
                    rs.getString("email")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar corretor por email: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public boolean cpfExiste(String cpf) {
        String sql = "SELECT COUNT(*) as total FROM corretores WHERE cpf = ?";
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
