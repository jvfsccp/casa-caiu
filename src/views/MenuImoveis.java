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
            scanner.nextLine();

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
        
        ArrayList<TipoImovel> tipos = tipoImovelDAO.listarTodos();
        if (tipos.isEmpty()) {
            System.out.println("📋 Nenhum tipo de imóvel cadastrado. Por favor, cadastre um tipo primeiro.");
            return;
        }
        
        System.out.println("Tipos disponíveis:");
        tipos.forEach(t -> System.out.printf("ID: %d | Descrição: %s\n", t.getId(), t.getDescricao()));
        System.out.print("Escolha o ID do tipo: ");
        int tipoImovelId = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        
        ArrayList<StatusImovel> status = statusImovelDAO.listarTodos();
        if (status.isEmpty()) {
            System.out.println("📋 Nenhum status de imóvel cadastrado. Por favor, cadastre um status primeiro.");
            return;
        }
        
        System.out.println("Status disponíveis:");
        status.forEach(s -> System.out.printf("ID: %d | Descrição: %s\n", s.getId(), s.getDescricao()));
        System.out.print("Escolha o ID do status: ");
        int statusImovelId = scanner.nextInt();
        scanner.nextLine();

        Imovel imovel = new Imovel(tipoImovelId, endereco, statusImovelId);
        if (imovelDAO.inserir(imovel)) {
            System.out.println("✅ Imóvel inserido com sucesso! ID: " + imovel.getId());
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
                ArrayList<TipoImovel> tipos = tipoImovelDAO.listarTodos();
                System.out.println("Tipos disponíveis:");
                tipos.forEach(t -> System.out.printf("ID: %d | Descrição: %s\n", t.getId(), t.getDescricao()));
                System.out.print("Escolha o ID do tipo: ");
                int tipoId = scanner.nextInt();
                scanner.nextLine();
                imoveis = imovelDAO.buscarPorTipo(tipoId);
                break;
            case 3:
                ArrayList<StatusImovel> status = statusImovelDAO.listarTodos();
                System.out.println("Status disponíveis:");
                status.forEach(s -> System.out.printf("ID: %d | Descrição: %s\n", s.getId(), s.getDescricao()));
                System.out.print("Escolha o ID do status: ");
                int statusId = scanner.nextInt();
                scanner.nextLine();
                imoveis = imovelDAO.buscarPorStatus(statusId);
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
            imoveis.forEach(imovel -> System.out.printf(
                "ID: %d | Endereço: %s | Tipo ID: %d | Status ID: %d\n",
                imovel.getId(),
                imovel.getEndereco(),
                imovel.getTipoImovelId(),
                imovel.getStatusImovelId()
            ));
        }
    }

    private void buscarImovel() {
        System.out.println("\n--- BUSCAR IMÓVEL ---");
        System.out.print("ID do Imóvel: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Imovel imovel = imovelDAO.buscarPorId(id);
        if (imovel != null) {
            System.out.println("✅ Imóvel encontrado:");
            System.out.printf(
                "ID: %d | Endereço: %s | Tipo ID: %d | Status ID: %d\n",
                imovel.getId(),
                imovel.getEndereco(),
                imovel.getTipoImovelId(),
                imovel.getStatusImovelId()
            );
        } else {
            System.out.println("❌ Imóvel com ID " + id + " não encontrado.");
        }
    }

    private void atualizarImovel() {
        System.out.println("\n--- ATUALIZAR IMÓVEL ---");
        System.out.print("ID do Imóvel: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Imovel imovel = imovelDAO.buscarPorId(id);
        if (imovel == null) {
            System.out.println("❌ Imóvel com ID " + id + " não encontrado.");
            return;
        }

        System.out.println("Imóvel atual: ");
        System.out.printf(
            "ID: %d | Endereço: %s | Tipo ID: %d | Status ID: %d\n",
            imovel.getId(),
            imovel.getEndereco(),
            imovel.getTipoImovelId(),
            imovel.getStatusImovelId()
        );
        
        ArrayList<TipoImovel> tipos = tipoImovelDAO.listarTodos();
        System.out.println("Tipos disponíveis:");
        tipos.forEach(t -> System.out.printf("ID: %d | Descrição: %s\n", t.getId(), t.getDescricao()));
        System.out.print("Novo ID do tipo (atual: " + imovel.getTipoImovelId() + ", Enter para manter): ");
        String tipoStr = scanner.nextLine();
        if (!tipoStr.isEmpty()) {
            imovel.setTipoImovelId(Integer.parseInt(tipoStr));
        }
        
        System.out.print("Novo Endereço (atual: " + imovel.getEndereco() + ", Enter para manter): ");
        String endereco = scanner.nextLine();
        if (!endereco.isEmpty()) {
            imovel.setEndereco(endereco);
        }
        
        ArrayList<StatusImovel> status = statusImovelDAO.listarTodos();
        System.out.println("Status disponíveis:");
        status.forEach(s -> System.out.printf("ID: %d | Descrição: %s\n", s.getId(), s.getDescricao()));
        System.out.print("Novo ID do status (atual: " + imovel.getStatusImovelId() + ", Enter para manter): ");
        String statusStr = scanner.nextLine();
        if (!statusStr.isEmpty()) {
            imovel.setStatusImovelId(Integer.parseInt(statusStr));
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
            System.out.println("❌ Imóvel com ID " + id + " não encontrado.");
            return;
        }

        System.out.println("Imóvel a ser deletado: ");
        System.out.printf(
            "ID: %d | Endereço: %s | Tipo ID: %d | Status ID: %d\n",
            imovel.getId(),
            imovel.getEndereco(),
            imovel.getTipoImovelId(),
            imovel.getStatusImovelId()
        );
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
                if (tipos.isEmpty()) {
                    System.out.println("📋 Nenhum tipo de imóvel cadastrado.");
                } else {
                    System.out.println("\n📋 Tipos cadastrados:");
                    tipos.forEach(t -> System.out.printf("ID: %d | Descrição: %s\n", t.getId(), t.getDescricao()));
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
                } else {
                    System.out.println("❌ Tipo de Imóvel com ID " + id + " não encontrado.");
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
                if (statusList.isEmpty()) {
                    System.out.println("📋 Nenhum status de imóvel cadastrado.");
                } else {
                    System.out.println("\n📋 Status cadastrados:");
                    statusList.forEach(s -> System.out.printf("ID: %d | Descrição: %s\n", s.getId(), s.getDescricao()));
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
                } else {
                    System.out.println("❌ Status de Imóvel com ID " + id + " não encontrado.");
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
