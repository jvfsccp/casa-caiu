package views;

import db.ClienteDAO;
import db.ImovelDAO;
import db.InteresseDAO;
import models.Interesse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuInteresses {
    private Scanner scanner;
    private InteresseDAO interesseDAO;
    private ClienteDAO clienteDAO;
    private ImovelDAO imovelDAO;
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public MenuInteresses() {
        this.scanner = new Scanner(System.in);
        this.interesseDAO = new InteresseDAO();
        this.clienteDAO = new ClienteDAO();
        this.imovelDAO = new ImovelDAO();
    }

    public void exibir() {
        int opcao = -1;
        do {
            System.out.println("\n--- Menu de Interesses ---");
            System.out.println("1. Registrar Novo Interesse");
            System.out.println("2. Listar Todos os Interesses");
            System.out.println("3. Listar Interesses por Cliente");
            System.out.println("4. Listar Interesses por Imóvel");
            System.out.println("5. Excluir Interesse");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("❌ Erro: Por favor, digite apenas números.");
                opcao = -1;
            } finally {
                scanner.nextLine();
            }

            switch (opcao) {
                case 1:
                    registrarInteresse();
                    break;
                case 2:
                    listarTodos();
                    break;
                case 3:
                    listarPorCliente();
                    break;
                case 4:
                    listarPorImovel();
                    break;
                case 5:
                    excluirInteresse();
                    break;
                case 0:
                    break;
                case -1:
                    break;
                default:
                    System.out.println("❌ Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void registrarInteresse() {
        System.out.println("\n--- Registrar Novo Interesse ---");
        try {
            System.out.print("ID do Cliente: ");
            int clienteId = scanner.nextInt();
            
            if (clienteDAO.buscarPorId(clienteId) == null) {
                System.out.println("❌ Erro: Cliente com ID " + clienteId + " não encontrado.");
                scanner.nextLine();
                return;
            }

            System.out.print("ID do Imóvel: ");
            int imovelId = scanner.nextInt();
            scanner.nextLine();

            if (imovelDAO.buscarPorId(imovelId) == null) {
                System.out.println("❌ Erro: Imóvel com ID " + imovelId + " não encontrado.");
                return;
            }

            Interesse interesse = new Interesse(clienteId, imovelId, LocalDate.now());
            if (interesseDAO.inserir(interesse)) {
                System.out.println("✅ Interesse registrado com sucesso!");
            }

        } catch (InputMismatchException e) {
            System.out.println("❌ Erro: IDs devem ser números.");
            scanner.nextLine();
        }
    }

    private void listarTodos() {
        System.out.println("\n--- Lista de Todos os Interesses ---");
        ArrayList<Interesse> interesses = interesseDAO.listarTodos();
        if (interesses.isEmpty()) {
            System.out.println("Nenhum interesse registrado.");
        } else {
            System.out.println("Interesses encontrados:");
            interesses.forEach(i -> System.out.printf(
                "ID: %d | Cliente ID: %d | Imóvel ID: %d | Data: %s\n",
                i.getId(), i.getClienteId(), i.getImovelId(), i.getDataInteresse().format(dateFormatter)
            ));
        }
    }

    private void listarPorCliente() {
        System.out.println("\n--- Listar Interesses por Cliente ---");
        try {
            System.out.print("Digite o ID do Cliente: ");
            int clienteId = scanner.nextInt();
            scanner.nextLine();

            if (clienteDAO.buscarPorId(clienteId) == null) {
                System.out.println("❌ Erro: Cliente com ID " + clienteId + " não encontrado.");
                return;
            }

            ArrayList<Interesse> interesses = interesseDAO.buscarPorCliente(clienteId);
            if (interesses.isEmpty()) {
                System.out.println("Nenhum interesse encontrado para este cliente.");
            } else {
                System.out.println("Interesses encontrados:");
                interesses.forEach(i -> System.out.printf(
                    "ID do Interesse: %d | ID do Imóvel: %d | Data: %s\n",
                    i.getId(), i.getImovelId(), i.getDataInteresse().format(dateFormatter)
                ));
            }
        } catch (InputMismatchException e) {
            System.out.println("❌ Erro: ID do cliente deve ser um número.");
            scanner.nextLine();
        }
    }
    
    private void listarPorImovel() {
        System.out.println("\n--- Listar Interesses por Imóvel ---");
        try {
            System.out.print("Digite o ID do Imóvel: ");
            int imovelId = scanner.nextInt();
            scanner.nextLine();

            if (imovelDAO.buscarPorId(imovelId) == null) {
                System.out.println("❌ Erro: Imóvel com ID " + imovelId + " não encontrado.");
                return;
            }

            ArrayList<Interesse> interesses = interesseDAO.buscarPorImovel(imovelId);
            if (interesses.isEmpty()) {
                System.out.println("Nenhum interesse encontrado para este imóvel.");
            } else {
                System.out.println("Interesses encontrados:");
                interesses.forEach(i -> System.out.printf(
                    "ID do Interesse: %d | ID do Cliente: %d | Data: %s\n",
                    i.getId(), i.getClienteId(), i.getDataInteresse().format(dateFormatter)
                ));
            }
        } catch (InputMismatchException e) {
            System.out.println("❌ Erro: ID do imóvel deve ser um número.");
            scanner.nextLine();
        }
    }

    private void excluirInteresse() {
        System.out.println("\n--- Excluir Interesse ---");
        try {
            System.out.print("Digite o ID do registro de interesse a ser excluído: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            if (interesseDAO.excluir(id)) {
                System.out.println("✅ Interesse excluído com sucesso!");
            } else {
                System.out.println("❌ Erro: Interesse com ID " + id + " não encontrado.");
            }
        } catch (InputMismatchException e) {
            System.out.println("❌ Erro: ID deve ser um número.");
            scanner.nextLine();
        }
    }
}
