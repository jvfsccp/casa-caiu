package views;

import db.VisitaDAO;
import models.Visita;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuVisitas {
    private Scanner scanner;
    private VisitaDAO visitaDAO;
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public MenuVisitas() {
        this.scanner = new Scanner(System.in);
        this.visitaDAO = new VisitaDAO();
    }

    public void exibir() {
        int opcao = -1;
        do {
            System.out.println("\n--- Menu de Visitas ---");
            System.out.println("1. Agendar Nova Visita");
            System.out.println("2. Listar Todas as Visitas");
            System.out.println("3. Atualizar Visita");
            System.out.println("4. Cancelar Visita");
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
                    agendarVisita();
                    break;
                case 2:
                    listarVisitas();
                    break;
                case 3:
                    atualizarVisita();
                    break;
                case 4:
                    cancelarVisita();
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

    private void agendarVisita() {
        System.out.println("\n--- Agendar Nova Visita ---");
        try {
            System.out.print("ID do Imóvel: ");
            int imovelId = scanner.nextInt();
            System.out.print("ID do Cliente: ");
            int clienteId = scanner.nextInt();
            System.out.print("ID do Corretor: ");
            int corretorId = scanner.nextInt();
            scanner.nextLine();
            
            System.out.print("Data da Visita (dd/MM/yyyy): ");
            String dataStr = scanner.nextLine();
            LocalDate dataVisita = LocalDate.parse(dataStr, dateFormatter);

            Visita visita = new Visita(imovelId, clienteId, corretorId, dataVisita);
            if (visitaDAO.inserir(visita)) {
                System.out.println("✅ Visita agendada com sucesso!");
            } else {
                System.out.println("❌ Erro ao agendar visita. Verifique se os IDs de imóvel, cliente e corretor são válidos.");
            }
        } catch (InputMismatchException e) {
            System.out.println("❌ Erro: ID deve ser um número.");
            scanner.nextLine();
        } catch (DateTimeParseException e) {
            System.out.println("❌ Erro: Formato de data inválido. Use dd/MM/yyyy.");
        }
    }

    private void listarVisitas() {
        System.out.println("\n--- Lista de Visitas Agendadas ---");
        ArrayList<Visita> visitas = visitaDAO.listarTodos();
        if (visitas.isEmpty()) {
            System.out.println("📋 Nenhuma visita agendada.");
        } else {
            visitas.forEach(v -> System.out.printf(
                "ID: %d | Data: %s | Imóvel ID: %d | Cliente ID: %d | Corretor ID: %d\n",
                v.getId(),
                v.getDataVisita().format(dateFormatter),
                v.getImovelId(),
                v.getClienteId(),
                v.getCorretorId()
            ));
        }
    }

    private void atualizarVisita() {
        System.out.println("\n--- Atualizar Visita ---");
        try {
            System.out.print("Digite o ID da visita que deseja atualizar: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            Visita visita = visitaDAO.buscarPorId(id);
            if (visita == null) {
                System.out.println("❌ Visita com ID " + id + " não encontrada.");
                return;
            }

            System.out.print("Nova data da Visita (dd/MM/yyyy) (Deixe em branco para manter a atual: " + visita.getDataVisita().format(dateFormatter) + "): ");
            String dataStr = scanner.nextLine();
            if (!dataStr.trim().isEmpty()) {
                visita.setDataVisita(LocalDate.parse(dataStr, dateFormatter));
            }

            if (visitaDAO.atualizar(visita)) {
                System.out.println("✅ Visita atualizada com sucesso!");
            } else {
                System.out.println("❌ Erro ao atualizar visita.");
            }
        } catch (InputMismatchException e) {
            System.out.println("❌ Erro: ID deve ser um número.");
            scanner.nextLine();
        } catch (DateTimeParseException e) {
            System.out.println("❌ Erro: Formato de data inválido.");
        }
    }

    private void cancelarVisita() {
        System.out.println("\n--- Cancelar Visita ---");
        try {
            System.out.print("Digite o ID da visita que deseja cancelar: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            if (visitaDAO.excluir(id)) {
                System.out.println("✅ Visita cancelada com sucesso!");
            } else {
                System.out.println("❌ Erro: Visita com ID " + id + " não encontrada.");
            }
        } catch (InputMismatchException e) {
            System.out.println("❌ Erro: ID deve ser um número.");
            scanner.nextLine();
        }
    }
}
