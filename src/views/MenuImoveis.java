package views;

import db.ImovelDAO;
import db.TipoImovelDAO;
import db.StatusImovelDAO;
import models.Imovel;
import models.TipoImovel;
import models.StatusImovel;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuImoveis {
    private ImovelDAO imovelDAO;
    private TipoImovelDAO tipoImovelDAO;
    private StatusImovelDAO statusImovelDAO;
    private Scanner scanner;

    public MenuImoveis() {
        this.imovelDAO = new ImovelDAO();
        this.tipoImovelDAO = new TipoImovelDAO();
        this.statusImovelDAO = new StatusImovelDAO();
        this.scanner = new Scanner(System.in);
    }

    public void exibir() {
        int opcao;
        do {
            System.out.println("\n╔════════════════════════════════════╗");
            System.out.println("║       GERENCIAR IMÓVEIS           ║");
            System.out.println("╚════════════════════════════════════╝");
            System.out.println("1. Inserir Imóvel");
            System.out.println("2. Listar Imóveis");
            System.out.println("3. Buscar Imóvel");
            System.out.println("4. Atualizar Imóvel");
            System.out.println("5. Deletar Imóvel");
            System.out.println("6. Total de Imóveis");
            System.out.println("7. Gerenciar Tipos de Imóvel");
            System.out.println("8. Gerenciar Status de Imóvel");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    inserirImovel();
                    break;
                case 2:
                    listarImoveis();
                    break;
                case 3:
                    buscarImovel();
                    break;
                case 4:
                    atualizarImovel();
                    break;
                case 5:
                    deletarImovel();
                    break;
                case 6:
                    System.out.println("\n📊 Total de imóveis cadastrados: " + imovelDAO.contarImoveis());
                    break;
                case 7:
                    gerenciarTipos();
                    break;
                case 8:
                    gerenciarStatus();
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("❌ Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void inserirImovel() {
        System.out.println("\n--- INSERIR IMÓVEL ---");
        
        // Listar tipos disponíveis
        ArrayList<TipoImovel> tipos = tipoImovelDAO.listarTodos();
        if (tipos.isEmpty()) {
            System.out.println("❌ Nenhum tipo de imóvel cadastrado! Cadastre primeiro.");
            return;
        }
        
        System.out.println("Tipos disponíveis:");
        for (TipoImovel t : tipos) {
            System.out.println(t.getIdTipo() + " - " + t.getDescricao());
        }
        System.out.print("Escolha o ID do tipo: ");
        int idTipo = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        
        // Listar status disponíveis
        ArrayList<StatusImovel> status = statusImovelDAO.listarTodos();
        if (status.isEmpty()) {
            System.out.println("❌ Nenhum status de imóvel cadastrado! Cadastre primeiro.");
            return;
        }
        
        System.out.println("Status disponíveis:");
        for (StatusImovel s : status) {
            System.out.println(s.getIdStatus() + " - " + s.getDescricao());
        }
        System.out.print("Escolha o ID do status: ");
        int idStatus = scanner.nextInt();
        scanner.nextLine();

        Imovel imovel = new Imovel(idTipo, endereco, idStatus);
        if (imovelDAO.inserir(imovel)) {
            System.out.println("✅ Imóvel inserido com sucesso! ID: " + imovel.getIdImovel());
        } else {
            System.out.println("❌ Erro ao inserir imóvel!");
        }
    }

    private void listarImoveis() {
        System.out.println("\n--- LISTAR IMÓVEIS ---");
        System.out.println("1. Listar Todos");
        System.out.println("2. Listar por Tipo");
        System.out.println("3. Listar por Status");
        System.out.println("4. Buscar por Endereço");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        ArrayList<Imovel> imoveis = new ArrayList<>();

        switch (opcao) {
            case 1:
                imoveis = imovelDAO.listarTodos();
                break;
            case 2:
                // Listar tipos disponíveis
                ArrayList<TipoImovel> tipos = tipoImovelDAO.listarTodos();
                System.out.println("Tipos disponíveis:");
                for (TipoImovel t : tipos) {
                    System.out.println(t.getIdTipo() + " - " + t.getDescricao());
                }
                System.out.print("Escolha o ID do tipo: ");
                int idTipo = scanner.nextInt();
                scanner.nextLine();
                imoveis = imovelDAO.buscarPorTipo(idTipo);
                break;
            case 3:
                // Listar status disponíveis
                ArrayList<StatusImovel> status = statusImovelDAO.listarTodos();
                System.out.println("Status disponíveis:");
                for (StatusImovel s : status) {
                    System.out.println(s.getIdStatus() + " - " + s.getDescricao());
                }
                System.out.print("Escolha o ID do status: ");
                int idStatus = scanner.nextInt();
                scanner.nextLine();
                imoveis = imovelDAO.buscarPorStatus(idStatus);
                break;
            case 4:
                System.out.print("Digite parte do endereço: ");
                String endereco = scanner.nextLine();
                imoveis = imovelDAO.buscarPorEndereco(endereco);
                break;
            default:
                System.out.println("❌ Opção inválida!");
                return;
        }

        if (imoveis.isEmpty()) {
            System.out.println("📋 Nenhum imóvel encontrado.");
        } else {
            System.out.println("\n📋 Imóveis encontrados: " + imoveis.size());
            System.out.println("════════════════════════════════════════════════════════════");
            for (Imovel imovel : imoveis) {
                System.out.println(imovel);
                System.out.println("────────────────────────────────────────────────────────────");
            }
        }
    }

    private void buscarImovel() {
        System.out.println("\n--- BUSCAR IMÓVEL ---");
        System.out.print("ID do Imóvel: ");
        int id = scanner.nextInt();

        Imovel imovel = imovelDAO.buscarPorId(id);
        if (imovel != null) {
            System.out.println("✅ Imóvel encontrado:");
            System.out.println(imovel);
        } else {
            System.out.println("❌ Imóvel não encontrado!");
        }
    }

    private void atualizarImovel() {
        System.out.println("\n--- ATUALIZAR IMÓVEL ---");
        System.out.print("ID do Imóvel: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Imovel imovel = imovelDAO.buscarPorId(id);
        if (imovel == null) {
            System.out.println("❌ Imóvel não encontrado!");
            return;
        }

        System.out.println("Imóvel atual: " + imovel);
        
        // Atualizar tipo
        ArrayList<TipoImovel> tipos = tipoImovelDAO.listarTodos();
        System.out.println("Tipos disponíveis:");
        for (TipoImovel t : tipos) {
            System.out.println(t.getIdTipo() + " - " + t.getDescricao());
        }
        System.out.print("Novo ID do tipo (atual: " + imovel.getIdTipo() + ", Enter para manter): ");
        String tipoStr = scanner.nextLine();
        if (!tipoStr.isEmpty()) {
            imovel.setIdTipo(Integer.parseInt(tipoStr));
        }
        
        System.out.print("Novo Endereço (atual: " + imovel.getEndereco() + ", Enter para manter): ");
        String endereco = scanner.nextLine();
        if (!endereco.isEmpty()) {
            imovel.setEndereco(endereco);
        }
        
        // Atualizar status
        ArrayList<StatusImovel> status = statusImovelDAO.listarTodos();
        System.out.println("Status disponíveis:");
        for (StatusImovel s : status) {
            System.out.println(s.getIdStatus() + " - " + s.getDescricao());
        }
        System.out.print("Novo ID do status (atual: " + imovel.getIdStatus() + ", Enter para manter): ");
        String statusStr = scanner.nextLine();
        if (!statusStr.isEmpty()) {
            imovel.setIdStatus(Integer.parseInt(statusStr));
        }

        if (imovelDAO.atualizar(imovel)) {
            System.out.println("✅ Imóvel atualizado com sucesso!");
        } else {
            System.out.println("❌ Erro ao atualizar imóvel!");
        }
    }

    private void deletarImovel() {
        System.out.println("\n--- DELETAR IMÓVEL ---");
        System.out.print("ID do Imóvel: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Imovel imovel = imovelDAO.buscarPorId(id);
        if (imovel == null) {
            System.out.println("❌ Imóvel não encontrado!");
            return;
        }

        System.out.println("Imóvel a ser deletado: " + imovel);
        System.out.print("Confirma a exclusão? (S/N): ");
        String confirmacao = scanner.next();

        if (confirmacao.equalsIgnoreCase("S")) {
            if (imovelDAO.excluir(id)) {
                System.out.println("✅ Imóvel deletado com sucesso!");
            } else {
                System.out.println("❌ Erro ao deletar imóvel! (pode estar vinculado a visitas ou propostas)");
            }
        } else {
            System.out.println("❌ Exclusão cancelada.");
        }
    }

    private void gerenciarTipos() {
        System.out.println("\n--- GERENCIAR TIPOS DE IMÓVEL ---");
        System.out.println("1. Listar Tipos");
        System.out.println("2. Inserir Tipo");
        System.out.println("3. Atualizar Tipo");
        System.out.println("4. Deletar Tipo");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                ArrayList<TipoImovel> tipos = tipoImovelDAO.listarTodos();
                System.out.println("\n📋 Tipos cadastrados:");
                for (TipoImovel t : tipos) {
                    System.out.println(t);
                }
                break;
            case 2:
                System.out.print("Descrição do tipo: ");
                String desc = scanner.nextLine();
                TipoImovel tipo = new TipoImovel(desc);
                if (tipoImovelDAO.inserir(tipo)) {
                    System.out.println("✅ Tipo inserido com sucesso!");
                } else {
                    System.out.println("❌ Erro ao inserir tipo!");
                }
                break;
            case 3:
                System.out.print("ID do tipo: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                TipoImovel tipoAtual = tipoImovelDAO.buscarPorId(id);
                if (tipoAtual != null) {
                    System.out.print("Nova descrição: ");
                    tipoAtual.setDescricao(scanner.nextLine());
                    if (tipoImovelDAO.atualizar(tipoAtual)) {
                        System.out.println("✅ Tipo atualizado!");
                    }
                }
                break;
            case 4:
                System.out.print("ID do tipo a deletar: ");
                int idDel = scanner.nextInt();
                if (tipoImovelDAO.excluir(idDel)) {
                    System.out.println("✅ Tipo deletado!");
                } else {
                    System.out.println("❌ Erro ao deletar! (pode estar em uso)");
                }
                break;
        }
    }

    private void gerenciarStatus() {
        System.out.println("\n--- GERENCIAR STATUS DE IMÓVEL ---");
        System.out.println("1. Listar Status");
        System.out.println("2. Inserir Status");
        System.out.println("3. Atualizar Status");
        System.out.println("4. Deletar Status");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                ArrayList<StatusImovel> statusList = statusImovelDAO.listarTodos();
                System.out.println("\n📋 Status cadastrados:");
                for (StatusImovel s : statusList) {
                    System.out.println(s);
                }
                break;
            case 2:
                System.out.print("Descrição do status: ");
                String desc = scanner.nextLine();
                StatusImovel status = new StatusImovel(desc);
                if (statusImovelDAO.inserir(status)) {
                    System.out.println("✅ Status inserido com sucesso!");
                } else {
                    System.out.println("❌ Erro ao inserir status!");
                }
                break;
            case 3:
                System.out.print("ID do status: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                StatusImovel statusAtual = statusImovelDAO.buscarPorId(id);
                if (statusAtual != null) {
                    System.out.print("Nova descrição: ");
                    statusAtual.setDescricao(scanner.nextLine());
                    if (statusImovelDAO.atualizar(statusAtual)) {
                        System.out.println("✅ Status atualizado!");
                    }
                }
                break;
            case 4:
                System.out.print("ID do status a deletar: ");
                int idDel = scanner.nextInt();
                if (statusImovelDAO.excluir(idDel)) {
                    System.out.println("✅ Status deletado!");
                } else {
                    System.out.println("❌ Erro ao deletar! (pode estar em uso)");
                }
                break;
        }
    }

    public void fecharRecursos() {
        imovelDAO.fecharConexao();
        tipoImovelDAO.fecharConexao();
        statusImovelDAO.fecharConexao();
    }
}
