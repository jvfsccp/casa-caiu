package db;

import models.TipoImovel;
import models.StatusImovel;

public class PopularDados {
    
    public static void popularTiposImovel() {
        TipoImovelDAO dao = new TipoImovelDAO();
        
        // Verificar se já existem dados
        if (!dao.listarTodos().isEmpty()) {
            System.out.println("✅ Tipos de imóvel já cadastrados.");
            return;
        }
        
        System.out.println("📝 Populando tipos de imóvel...");
        
        String[] tipos = {"Casa", "Apartamento", "Sala Comercial", "Terreno", "Chácara", "Galpão"};
        
        for (String tipo : tipos) {
            TipoImovel tipoImovel = new TipoImovel(tipo);
            if (dao.inserir(tipoImovel)) {
                System.out.println("  ✅ Tipo inserido: " + tipo);
            } else {
                System.out.println("  ❌ Erro ao inserir: " + tipo);
            }
        }
        
        dao.fecharConexao();
    }
    
    public static void popularStatusImovel() {
        StatusImovelDAO dao = new StatusImovelDAO();
        
        // Verificar se já existem dados
        if (!dao.listarTodos().isEmpty()) {
            System.out.println("✅ Status de imóvel já cadastrados.");
            return;
        }
        
        System.out.println("📝 Populando status de imóvel...");
        
        String[] status = {"Disponível", "Vendido", "Alugado", "Reservado", "Em Reforma", "Indisponível"};
        
        for (String st : status) {
            StatusImovel statusImovel = new StatusImovel(st);
            if (dao.inserir(statusImovel)) {
                System.out.println("  ✅ Status inserido: " + st);
            } else {
                System.out.println("  ❌ Erro ao inserir: " + st);
            }
        }
        
        dao.fecharConexao();
    }
    
    public static void popularTudo() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║  POPULANDO DADOS INICIAIS DO SISTEMA  ║");
        System.out.println("╚════════════════════════════════════════╝\n");
        
        popularTiposImovel();
        popularStatusImovel();
        
        System.out.println("\n✅ Processo de população de dados concluído!");
    }
}
