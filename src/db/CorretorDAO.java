package db;

import models.Corretor;
import java.sql.*;
import java.util.ArrayList;

public class CorretorDAO {
    private Connection connection;

    public CorretorDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    // CREATE
    public boolean inserir(Corretor corretor) {
        String sql = "INSERT INTO corretor (cpf, nome, creci, telefone, email) VALUES (?, ?, ?, ?, ?)";
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
                    corretor.setIdCorretor(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir corretor: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // READ
    public Corretor buscarPorId(int idCorretor) {
        String sql = "SELECT * FROM corretor WHERE id_corretor = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCorretor);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Corretor(
                    rs.getInt("id_corretor"),
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
        String sql = "SELECT * FROM corretor ORDER BY nome";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                corretores.add(new Corretor(
                    rs.getInt("id_corretor"),
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

    // UPDATE
    public boolean atualizar(Corretor corretor) {
        String sql = "UPDATE corretor SET cpf = ?, nome = ?, creci = ?, telefone = ?, email = ? WHERE id_corretor = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, corretor.getCpf());
            stmt.setString(2, corretor.getNome());
            stmt.setString(3, corretor.getCreci());
            stmt.setString(4, corretor.getTelefone());
            stmt.setString(5, corretor.getEmail());
            stmt.setInt(6, corretor.getIdCorretor());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar corretor: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // DELETE
    public boolean excluir(int idCorretor) {
        String sql = "DELETE FROM corretor WHERE id_corretor = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCorretor);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao excluir corretor: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // CONSULTAS ESPECIAIS
    public Corretor buscarPorCpf(String cpf) {
        String sql = "SELECT * FROM corretor WHERE cpf = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Corretor(
                    rs.getInt("id_corretor"),
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
        String sql = "SELECT * FROM corretor WHERE creci = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, creci);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Corretor(
                    rs.getInt("id_corretor"),
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
        String sql = "SELECT * FROM corretor WHERE nome LIKE ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                corretores.add(new Corretor(
                    rs.getInt("id_corretor"),
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
        String sql = "SELECT * FROM corretor WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Corretor(
                    rs.getInt("id_corretor"),
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
        String sql = "SELECT COUNT(*) as total FROM corretor WHERE cpf = ?";
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
        String sql = "SELECT COUNT(*) as total FROM corretor WHERE creci = ?";
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
        String sql = "SELECT COUNT(*) as total FROM corretor WHERE email = ?";
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
        String sql = "SELECT COUNT(*) as total FROM corretor";
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
