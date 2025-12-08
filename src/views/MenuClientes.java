package views;

import db.ClienteDAO;
import models.Cliente;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuClientes {
    private ClienteDAO clienteDAO;
    private Scanner scanner;

    public MenuClientes() {
        this.clienteDAO = new ClienteDAO();
        this.scanner = new Scanner(System.in);
    }

    public void exibir() {
        int opcao;
        do {
            System.out.println("\n╔════════════════════════════════════╗");
            System.out.println("║       GERENCIAR CLIENTES          ║");
            System.out.println("╚════════════════════════════════════╝");
            System.out.println("1. Inserir Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Buscar Cliente");
            System.out.println("4. Atualizar Cliente");
            System.out.println("5. Deletar Cliente");
            System.out.println("6. Total de Clientes");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    inserirCliente();
                    break;
                case 2:
                    listarClientes();
                    break;
                case 3:
                    buscarCliente();
                    break;
                case 4:
                    atualizarCliente();
                    break;
                case 5:
                    deletarCliente();
                    break;
                case 6:
                    System.out.println("\n📊 Total de clientes cadastrados: " + clienteDAO.contarClientes());
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("❌ Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void inserirCliente() {
        System.out.println("\n--- INSERIR CLIENTE ---");
        
        String cpf;
        do {
            System.out.print("CPF (11 dígitos): ");
            cpf = scanner.nextLine();
            if (cpf.length() != 11) {
                System.out.println("❌ CPF deve ter exatamente 11 dígitos!");
                continue;
            }
            if (clienteDAO.cpfExiste(cpf)) {
                System.out.println("❌ CPF já cadastrado!");
                return;
            }
            break;
        } while (true);
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();

        if (clienteDAO.emailExiste(email)) {
            System.out.println("❌ Email já cadastrado!");
            return;
        }

        Cliente cliente = new Cliente(cpf, nome, telefone, email);
        if (clienteDAO.inserir(cliente)) {
            System.out.println("✅ Cliente inserido com sucesso! ID: " + cliente.getId());
        } else {
            System.out.println("❌ Erro ao inserir cliente!");
        }
    }

    private void listarClientes() {
        System.out.println("\n--- LISTAR CLIENTES ---");
        System.out.println("1. Listar Todos");
        System.out.println("2. Buscar por Nome");
        System.out.println("3. Buscar por CPF");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        ArrayList<Cliente> clientes = new ArrayList<>();

        switch (opcao) {
            case 1:
                clientes = clienteDAO.listarTodos();
                break;
            case 2:
                System.out.print("Digite o nome (ou parte dele): ");
                String nome = scanner.nextLine();
                clientes = clienteDAO.buscarPorNome(nome);
                break;
            case 3:
                System.out.print("Digite o CPF: ");
                String cpf = scanner.nextLine();
                Cliente cliente = clienteDAO.buscarPorCpf(cpf);
                if (cliente != null) {
                    clientes.add(cliente);
                }
                break;
            default:
                System.out.println("❌ Opção inválida!");
                return;
        }

        if (clientes.isEmpty()) {
            System.out.println("📋 Nenhum cliente encontrado.");
        } else {
            System.out.println("\n📋 Clientes encontrados: " + clientes.size());
            clientes.forEach(c -> System.out.printf(
                "ID: %d | Nome: %s | CPF: %s | Telefone: %s | Email: %s\n",
                c.getId(), c.getNome(), c.getCpf(), c.getTelefone(), c.getEmail()
            ));
        }
    }

    private void buscarCliente() {
        System.out.println("\n--- BUSCAR CLIENTE ---");
        System.out.println("1. Buscar por ID");
        System.out.println("2. Buscar por Email");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        Cliente cliente = null;

        switch (opcao) {
            case 1:
                System.out.print("ID do Cliente: ");
                int id = scanner.nextInt();
                cliente = clienteDAO.buscarPorId(id);
                break;
            case 2:
                System.out.print("Email: ");
                String email = scanner.nextLine();
                cliente = clienteDAO.buscarPorEmail(email);
                break;
            default:
                System.out.println("❌ Opção inválida!");
                return;
        }

        if (cliente != null) {
            System.out.println("✅ Cliente encontrado:");
            System.out.printf(
                "ID: %d | Nome: %s | CPF: %s | Telefone: %s | Email: %s\n",
                cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getTelefone(), cliente.getEmail()
            );
        } else {
            System.out.println("❌ Cliente não encontrado!");
        }
    }

    private void atualizarCliente() {
        System.out.println("\n--- ATUALIZAR CLIENTE ---");
        System.out.print("ID do Cliente: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Cliente cliente = clienteDAO.buscarPorId(id);
        if (cliente == null) {
            System.out.println("❌ Cliente não encontrado!");
            return;
        }

        System.out.println("Cliente atual:");
        System.out.printf(
            "ID: %d | Nome: %s | CPF: %s | Telefone: %s | Email: %s\n",
            cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getTelefone(), cliente.getEmail()
        );
        
        System.out.print("Novo CPF (atual: " + cliente.getCpf() + "): ");
        String cpf = scanner.nextLine();
        if (!cpf.isEmpty() && cpf.length() != 11) {
            System.out.println("❌ CPF deve ter exatamente 11 dígitos!");
            return;
        }
        if (!cpf.isEmpty()) cliente.setCpf(cpf);
        
        System.out.print("Novo Nome (atual: " + cliente.getNome() + "): ");
        String nome = scanner.nextLine();
        if (!nome.isEmpty()) cliente.setNome(nome);
        
        System.out.print("Novo Telefone (atual: " + cliente.getTelefone() + "): ");
        String telefone = scanner.nextLine();
        if (!telefone.isEmpty()) cliente.setTelefone(telefone);
        
        System.out.print("Novo Email (atual: " + cliente.getEmail() + "): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) cliente.setEmail(email);

        if (clienteDAO.atualizar(cliente)) {
            System.out.println("✅ Cliente atualizado com sucesso!");
        } else {
            System.out.println("❌ Erro ao atualizar cliente!");
        }
    }

    private void deletarCliente() {
        System.out.println("\n--- DELETAR CLIENTE ---");
        System.out.print("ID do Cliente: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Cliente cliente = clienteDAO.buscarPorId(id);
        if (cliente == null) {
            System.out.println("❌ Cliente não encontrado!");
            return;
        }

        System.out.println("Cliente a ser deletado:");
        System.out.printf(
            "ID: %d | Nome: %s | CPF: %s | Telefone: %s | Email: %s\n",
            cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getTelefone(), cliente.getEmail()
        );
        System.out.print("Confirma a exclusão? (S/N): ");
        String confirmacao = scanner.next();

        if (confirmacao.equalsIgnoreCase("S")) {
            if (clienteDAO.excluir(id)) {
                System.out.println("✅ Cliente deletado com sucesso!");
            } else {
                System.out.println("❌ Erro ao deletar cliente! (pode estar vinculado a visitas ou propostas)");
            }
        } else {
            System.out.println("❌ Exclusão cancelada.");
        }
    }

    public void fecharRecursos() {
        clienteDAO.fecharConexao();
    }
}
