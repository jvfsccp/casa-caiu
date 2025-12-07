package views;

import db.PropostaDAO;
import models.Proposta;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuPropostas {
    private Scanner scanner;
    private PropostaDAO propostaDAO;
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public MenuPropostas() {
        this.scanner = new Scanner(System.in);
        this.propostaDAO = new PropostaDAO();
    }

    public void exibir() {
        int opcao = -1;
        do {
            System.out.println("\n--- Menu de Propostas ---");
            System.out.println("1. Criar Nova Proposta");
            System.out.println("2. Listar Todas as Propostas");
            System.out.println("3. Atualizar Proposta");
            System.out.println("4. Excluir Proposta");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("❌ Erro: Por favor, digite apenas números.");
                opcao = -1;
            } finally {
                scanner.nextLine(); // Limpa o buffer
            }

            switch (opcao) {
                case 1:
                    criarProposta();
                    break;
                case 2:
                    listarPropostas();
                    break;
                case 3:
                    atualizarProposta();
                    break;
                case 4:
                    excluirProposta();
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

    private void criarProposta() {
        System.out.println("\n--- Criar Nova Proposta ---");
        try {
            System.out.print("ID do Imóvel: ");
            int imovelId = scanner.nextInt();
            System.out.print("ID do Cliente: ");
            int clienteId = scanner.nextInt();
            scanner.nextLine(); // Limpa buffer
            
            System.out.print("Data da Proposta (dd/MM/yyyy): ");
            String dataStr = scanner.nextLine();
            LocalDate dataProposta = LocalDate.parse(dataStr, dateFormatter);

            Proposta proposta = new Proposta(imovelId, clienteId, dataProposta);
            if (propostaDAO.inserir(proposta)) {
                System.out.println("✅ Proposta criada com sucesso!");
            } else {
                System.out.println("❌ Erro ao criar proposta.");
            }
        } catch (InputMismatchException e) {
            System.out.println("❌ Erro: ID deve ser um número.");
            scanner.nextLine();
        } catch (DateTimeParseException e) {
            System.out.println("❌ Erro: Formato de data inválido. Use dd/MM/yyyy.");
        }
    }

    private void listarPropostas() {
        System.out.println("\n--- Lista de Propostas ---");
        ArrayList<Proposta> propostas = propostaDAO.listarTodos();
        if (propostas.isEmpty()) {
            System.out.println("Nenhuma proposta encontrada.");
        } else {
            propostas.forEach(p -> System.out.printf(
                "ID: %d | Data: %s | Imóvel ID: %d | Cliente ID: %d\n",
                p.getId(), // PADRONIZADO
                p.getDataProposta().format(dateFormatter),
                p.getImovelId(), // PADRONIZADO
                p.getClienteId() // PADRONIZADO
            ));
        }
    }

    private void atualizarProposta() {
        System.out.println("\n--- Atualizar Proposta ---");
        try {
            System.out.print("Digite o ID da proposta que deseja atualizar: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            Proposta proposta = propostaDAO.buscarPorId(id);
            if (proposta == null) {
                System.out.println("❌ Proposta não encontrada.");
                return;
            }

            System.out.print("Nova data da Proposta (dd/MM/yyyy) (Deixe em branco para manter a atual: " + proposta.getDataProposta().format(dateFormatter) + "): ");
            String dataStr = scanner.nextLine();
            if (!dataStr.trim().isEmpty()) {
                proposta.setDataProposta(LocalDate.parse(dataStr, dateFormatter));
            }

            if (propostaDAO.atualizar(proposta)) {
                System.out.println("✅ Proposta atualizada com sucesso!");
            } else {
                System.out.println("❌ Erro ao atualizar proposta.");
            }
        } catch (InputMismatchException e) {
            System.out.println("❌ Erro: ID deve ser um número.");
            scanner.nextLine();
        } catch (DateTimeParseException e) {
            System.out.println("❌ Erro: Formato de data inválido.");
        }
    }

    private void excluirProposta() {
        System.out.println("\n--- Excluir Proposta ---");
        try {
            System.out.print("Digite o ID da proposta que deseja excluir: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            if (propostaDAO.excluir(id)) {
                System.out.println("✅ Proposta excluída com sucesso!");
            } else {
                System.out.println("❌ Erro: Proposta não encontrada ou não pôde ser excluída.");
            }
        } catch (InputMismatchException e) {
            System.out.println("❌ Erro: ID deve ser um número.");
            scanner.nextLine();
        }
    }
}
