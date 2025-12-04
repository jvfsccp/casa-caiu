package db;

import models.*;
import java.sql.*;
import java.util.ArrayList;

public class ImovelDAO {
    private Connection connection;

    public ImovelDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    // CREATE - Inserir Casa
    public boolean inserirCasa(Casa casa) {
        String sql = "INSERT INTO imoveis (endereco, area, valor_base, tipo_transacao, tipo_imovel, numero_quartos, numero_vagas_garagem) VALUES (?, ?, ?, ?, 'Casa', ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, casa.getEndereco());
            stmt.setDouble(2, casa.getArea());
            stmt.setDouble(3, casa.getValorBase());
            stmt.setString(4, casa.getTipoTransacao());
            stmt.setInt(5, casa.getNumeroQuartos());
            stmt.setInt(6, casa.getNumeroVagasGaragem());
            
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    casa.setId(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir casa: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // CREATE - Inserir Apartamento
    public boolean inserirApartamento(Apartamento apartamento) {
        String sql = "INSERT INTO imoveis (endereco, area, valor_base, tipo_transacao, tipo_imovel, andar, valor_condominio) VALUES (?, ?, ?, ?, 'Apartamento', ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, apartamento.getEndereco());
            stmt.setDouble(2, apartamento.getArea());
            stmt.setDouble(3, apartamento.getValorBase());
            stmt.setString(4, apartamento.getTipoTransacao());
            stmt.setInt(5, apartamento.getAndar());
            stmt.setDouble(6, apartamento.getValorCondominio());
            
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    apartamento.setId(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir apartamento: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // CREATE - Inserir Sala Comercial
    public boolean inserirSalaComercial(SalaComercial sala) {
        String sql = "INSERT INTO imoveis (endereco, area, valor_base, tipo_transacao, tipo_imovel, tem_estacionamento) VALUES (?, ?, ?, ?, 'SalaComercial', ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, sala.getEndereco());
            stmt.setDouble(2, sala.getArea());
            stmt.setDouble(3, sala.getValorBase());
            stmt.setString(4, sala.getTipoTransacao());
            stmt.setBoolean(5, sala.isTemEstacionamento());
            
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    sala.setId(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir sala comercial: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // READ - Buscar imóvel por ID
    public Imovel buscarPorId(int id) {
        String sql = "SELECT * FROM imoveis WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return criarImovelDoResultSet(rs);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar imóvel por ID: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    // READ - Listar todos os imóveis
    public ArrayList<Imovel> listarTodos() {
        ArrayList<Imovel> imoveis = new ArrayList<>();
        String sql = "SELECT * FROM imoveis";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Imovel imovel = criarImovelDoResultSet(rs);
                if (imovel != null) {
                    imoveis.add(imovel);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar imóveis: " + e.getMessage());
            e.printStackTrace();
        }
        return imoveis;
    }

    // UPDATE - Atualizar Casa
    public boolean atualizarCasa(Casa casa) {
        String sql = "UPDATE imoveis SET endereco = ?, area = ?, valor_base = ?, tipo_transacao = ?, numero_quartos = ?, numero_vagas_garagem = ? WHERE id = ? AND tipo_imovel = 'Casa'";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, casa.getEndereco());
            stmt.setDouble(2, casa.getArea());
            stmt.setDouble(3, casa.getValorBase());
            stmt.setString(4, casa.getTipoTransacao());
            stmt.setInt(5, casa.getNumeroQuartos());
            stmt.setInt(6, casa.getNumeroVagasGaragem());
            stmt.setInt(7, casa.getId());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar casa: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // UPDATE - Atualizar Apartamento
    public boolean atualizarApartamento(Apartamento apartamento) {
        String sql = "UPDATE imoveis SET endereco = ?, area = ?, valor_base = ?, tipo_transacao = ?, andar = ?, valor_condominio = ? WHERE id = ? AND tipo_imovel = 'Apartamento'";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, apartamento.getEndereco());
            stmt.setDouble(2, apartamento.getArea());
            stmt.setDouble(3, apartamento.getValorBase());
            stmt.setString(4, apartamento.getTipoTransacao());
            stmt.setInt(5, apartamento.getAndar());
            stmt.setDouble(6, apartamento.getValorCondominio());
            stmt.setInt(7, apartamento.getId());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar apartamento: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // UPDATE - Atualizar Sala Comercial
    public boolean atualizarSalaComercial(SalaComercial sala) {
        String sql = "UPDATE imoveis SET endereco = ?, area = ?, valor_base = ?, tipo_transacao = ?, tem_estacionamento = ? WHERE id = ? AND tipo_imovel = 'SalaComercial'";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, sala.getEndereco());
            stmt.setDouble(2, sala.getArea());
            stmt.setDouble(3, sala.getValorBase());
            stmt.setString(4, sala.getTipoTransacao());
            stmt.setBoolean(5, sala.isTemEstacionamento());
            stmt.setInt(6, sala.getId());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar sala comercial: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // DELETE - Excluir imóvel
    public boolean excluir(int id) {
        String sql = "DELETE FROM imoveis WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao excluir imóvel: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // CONSULTA ESPECIAL - Buscar imóveis por tipo de transação
    public ArrayList<Imovel> buscarPorTipoTransacao(String tipoTransacao) {
        ArrayList<Imovel> imoveis = new ArrayList<>();
        String sql = "SELECT * FROM imoveis WHERE tipo_transacao = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, tipoTransacao);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Imovel imovel = criarImovelDoResultSet(rs);
                if (imovel != null) {
                    imoveis.add(imovel);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar imóveis por tipo de transação: " + e.getMessage());
            e.printStackTrace();
        }
        return imoveis;
    }

    // CONSULTA ESPECIAL - Buscar imóveis por tipo
    public ArrayList<Imovel> buscarPorTipoImovel(String tipoImovel) {
        ArrayList<Imovel> imoveis = new ArrayList<>();
        String sql = "SELECT * FROM imoveis WHERE tipo_imovel = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, tipoImovel);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Imovel imovel = criarImovelDoResultSet(rs);
                if (imovel != null) {
                    imoveis.add(imovel);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar imóveis por tipo: " + e.getMessage());
            e.printStackTrace();
        }
        return imoveis;
    }

    // CONSULTA ESPECIAL - Buscar imóveis por faixa de preço
    public ArrayList<Imovel> buscarPorFaixaPreco(double valorMinimo, double valorMaximo) {
        ArrayList<Imovel> imoveis = new ArrayList<>();
        String sql = "SELECT * FROM imoveis WHERE valor_base BETWEEN ? AND ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, valorMinimo);
            stmt.setDouble(2, valorMaximo);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Imovel imovel = criarImovelDoResultSet(rs);
                if (imovel != null) {
                    imoveis.add(imovel);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar imóveis por faixa de preço: " + e.getMessage());
            e.printStackTrace();
        }
        return imoveis;
    }

    // CONSULTA ESPECIAL - Buscar imóveis por área mínima
    public ArrayList<Imovel> buscarPorAreaMinima(double areaMinima) {
        ArrayList<Imovel> imoveis = new ArrayList<>();
        String sql = "SELECT * FROM imoveis WHERE area >= ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, areaMinima);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Imovel imovel = criarImovelDoResultSet(rs);
                if (imovel != null) {
                    imoveis.add(imovel);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar imóveis por área mínima: " + e.getMessage());
            e.printStackTrace();
        }
        return imoveis;
    }

    // CONSULTA ESPECIAL - Buscar casas por número de quartos
    public ArrayList<Casa> buscarCasasPorQuartos(int numeroQuartos) {
        ArrayList<Casa> casas = new ArrayList<>();
        String sql = "SELECT * FROM imoveis WHERE tipo_imovel = 'Casa' AND numero_quartos >= ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, numeroQuartos);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Casa casa = (Casa) criarImovelDoResultSet(rs);
                if (casa != null) {
                    casas.add(casa);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar casas por número de quartos: " + e.getMessage());
            e.printStackTrace();
        }
        return casas;
    }

    // Método auxiliar para criar objeto Imovel a partir do ResultSet
    private Imovel criarImovelDoResultSet(ResultSet rs) throws SQLException {
        String tipoImovel = rs.getString("tipo_imovel");
        
        switch (tipoImovel) {
            case "Casa":
                return new Casa(
                    rs.getInt("id"),
                    rs.getString("endereco"),
                    rs.getDouble("area"),
                    rs.getDouble("valor_base"),
                    rs.getString("tipo_transacao"),
                    rs.getInt("numero_quartos"),
                    rs.getInt("numero_vagas_garagem")
                );
            case "Apartamento":
                return new Apartamento(
                    rs.getInt("id"),
                    rs.getString("endereco"),
                    rs.getDouble("area"),
                    rs.getDouble("valor_base"),
                    rs.getString("tipo_transacao"),
                    rs.getInt("andar"),
                    rs.getDouble("valor_condominio")
                );
            case "SalaComercial":
                return new SalaComercial(
                    rs.getInt("id"),
                    rs.getString("endereco"),
                    rs.getDouble("area"),
                    rs.getDouble("valor_base"),
                    rs.getString("tipo_transacao"),
                    rs.getBoolean("tem_estacionamento")
                );
            default:
                return null;
        }
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
