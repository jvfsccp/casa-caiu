package views;

import db.CorretorDAO;
import models.Corretor;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuCorretores {
    private CorretorDAO corretorDAO;
    private Scanner scanner;

    public MenuCorretores() {
        this.corretorDAO = new CorretorDAO();
        this.scanner = new Scanner(System.in);
    }

    public void exibir() {
        int opcao;
        do {
            System.out.println("\n╔════════════════════════════════════╗");
            System.out.println("║      GERENCIAR CORRETORES         ║");
            System.out.println("╚════════════════════════════════════╝");
            System.out.println("1. Inserir Corretor");
            System.out.println("2. Listar Corretores");
            System.out.println("3. Buscar Corretor");
            System.out.println("4. Atualizar Corretor");
            System.out.println("5. Deletar Corretor");
            System.out.println("6. Total de Corretores");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    inserirCorretor();
                    break;
                case 2:
                    listarCorretores();
                    break;
                case 3:
                    buscarCorretor();
                    break;
                case 4:
                    atualizarCorretor();
                    break;
                case 5:
                    deletarCorretor();
                    break;
                case 6:
                    System.out.println("\n📊 Total de corretores cadastrados: " + corretorDAO.contarCorretores());
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("❌ Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void inserirCorretor() {
        System.out.println("\n--- INSERIR CORRETOR ---");
        
        String cpf;
        do {
            System.out.print("CPF (11 dígitos): ");
            cpf = scanner.nextLine();
            if (cpf.length() != 11) {
                System.out.println("❌ CPF deve ter exatamente 11 dígitos!");
                continue;
            }
            if (corretorDAO.cpfExiste(cpf)) {
                System.out.println("❌ CPF já cadastrado!");
                return;
            }
            break;
        } while (true);
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        String creci;
        do {
            System.out.print("CRECI: ");
            creci = scanner.nextLine();
            if (corretorDAO.creciExiste(creci)) {
                System.out.println("❌ CRECI já cadastrado!");
                return;
            }
            break;
        } while (true);
        
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();

        if (corretorDAO.emailExiste(email)) {
            System.out.println("❌ Email já cadastrado!");
            return;
        }

        Corretor corretor = new Corretor(cpf, nome, creci, telefone, email);
        if (corretorDAO.inserir(corretor)) {
            System.out.println("✅ Corretor inserido com sucesso! ID: " + corretor.getId());
        } else {
            System.out.println("❌ Erro ao inserir corretor!");
        }
    }

    private void listarCorretores() {
        System.out.println("\n--- LISTAR CORRETORES ---");
        System.out.println("1. Listar Todos (ordenados por nome)");
        System.out.println("2. Buscar por Nome");
        System.out.println("3. Buscar por CPF");
        System.out.println("4. Buscar por CRECI");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        ArrayList<Corretor> corretores = new ArrayList<>();

        switch (opcao) {
            case 1:
                corretores = corretorDAO.listarTodos();
                break;
            case 2:
                System.out.print("Digite o nome (ou parte dele): ");
                String nome = scanner.nextLine();
                corretores = corretorDAO.buscarPorNome(nome);
                break;
            case 3:
                System.out.print("Digite o CPF: ");
                String cpf = scanner.nextLine();
                Corretor corretor = corretorDAO.buscarPorCpf(cpf);
                if (corretor != null) {
                    corretores.add(corretor);
                }
                break;
            case 4:
                System.out.print("Digite o CRECI: ");
                String creci = scanner.nextLine();
                Corretor corretorCreci = corretorDAO.buscarPorCreci(creci);
                if (corretorCreci != null) {
                    corretores.add(corretorCreci);
                }
                break;
            default:
                System.out.println("❌ Opção inválida!");
                return;
        }

        if (corretores.isEmpty()) {
            System.out.println("📋 Nenhum corretor encontrado.");
        } else {
            System.out.println("\n📋 Corretores encontrados: " + corretores.size());
            corretores.forEach(c -> System.out.printf(
                "ID: %d | Nome: %s | CRECI: %s | CPF: %s | Telefone: %s | Email: %s\n",
                c.getId(), c.getNome(), c.getCreci(), c.getCpf(), c.getTelefone(), c.getEmail()
            ));
        }
    }

    private void buscarCorretor() {
        System.out.println("\n--- BUSCAR CORRETOR ---");
        System.out.println("1. Buscar por ID");
        System.out.println("2. Buscar por Email");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        Corretor corretor = null;
        String criterio = "";

        switch (opcao) {
            case 1:
                System.out.print("ID do Corretor: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                criterio = "ID " + id;
                corretor = corretorDAO.buscarPorId(id);
                break;
            case 2:
                System.out.print("Email: ");
                String email = scanner.nextLine();
                criterio = "email " + email;
                corretor = corretorDAO.buscarPorEmail(email);
                break;
            default:
                System.out.println("❌ Opção inválida!");
                return;
        }

        if (corretor != null) {
            System.out.println("✅ Corretor encontrado:");
            System.out.printf(
                "ID: %d | Nome: %s | CRECI: %s | CPF: %s | Telefone: %s | Email: %s\n",
                corretor.getId(), corretor.getNome(), corretor.getCreci(), corretor.getCpf(), corretor.getTelefone(), corretor.getEmail()
            );
        } else {
            System.out.println("❌ Corretor com " + criterio + " não encontrado.");
        }
    }

    private void atualizarCorretor() {
        System.out.println("\n--- ATUALIZAR CORRETOR ---");
        System.out.print("ID do Corretor: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Corretor corretor = corretorDAO.buscarPorId(id);
        if (corretor == null) {
            System.out.println("❌ Corretor com ID " + id + " não encontrado.");
            return;
        }

        System.out.println("Corretor atual:");
        System.out.printf(
            "ID: %d | Nome: %s | CRECI: %s | CPF: %s | Telefone: %s | Email: %s\n",
            corretor.getId(), corretor.getNome(), corretor.getCreci(), corretor.getCpf(), corretor.getTelefone(), corretor.getEmail()
        );
        
        System.out.print("Novo CPF (atual: " + corretor.getCpf() + "): ");
        String cpf = scanner.nextLine();
        if (!cpf.isEmpty() && cpf.length() != 11) {
            System.out.println("❌ CPF deve ter exatamente 11 dígitos!");
            return;
        }
        if (!cpf.isEmpty()) corretor.setCpf(cpf);
        
        System.out.print("Novo Nome (atual: " + corretor.getNome() + "): ");
        String nome = scanner.nextLine();
        if (!nome.isEmpty()) corretor.setNome(nome);
        
        System.out.print("Novo CRECI (atual: " + corretor.getCreci() + "): ");
        String creci = scanner.nextLine();
        if (!creci.isEmpty()) corretor.setCreci(creci);
        
        System.out.print("Novo Telefone (atual: " + corretor.getTelefone() + "): ");
        String telefone = scanner.nextLine();
        if (!telefone.isEmpty()) corretor.setTelefone(telefone);
        
        System.out.print("Novo Email (atual: " + corretor.getEmail() + "): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) corretor.setEmail(email);

        if (corretorDAO.atualizar(corretor)) {
            System.out.println("✅ Corretor atualizado com sucesso!");
        } else {
            System.out.println("❌ Erro ao atualizar corretor!");
        }
    }

    private void deletarCorretor() {
        System.out.println("\n--- DELETAR CORRETOR ---");
        System.out.print("ID do Corretor: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Corretor corretor = corretorDAO.buscarPorId(id);
        if (corretor == null) {
            System.out.println("❌ Corretor com ID " + id + " não encontrado.");
            return;
        }

        System.out.println("Corretor a ser deletado:");
        System.out.printf(
            "ID: %d | Nome: %s | CRECI: %s | CPF: %s | Telefone: %s | Email: %s\n",
            corretor.getId(), corretor.getNome(), corretor.getCreci(), corretor.getCpf(), corretor.getTelefone(), corretor.getEmail()
        );
        System.out.print("Confirma a exclusão? (S/N): ");
        String confirmacao = scanner.next();

        if (confirmacao.equalsIgnoreCase("S")) {
            if (corretorDAO.excluir(id)) {
                System.out.println("✅ Corretor deletado com sucesso!");
            } else {
                System.out.println("❌ Erro ao deletar corretor! (pode estar vinculado a visitas)");
            }
        } else {
            System.out.println("❌ Exclusão cancelada.");
        }
    }

    public void fecharRecursos() {
        corretorDAO.fecharConexao();
    }
}
