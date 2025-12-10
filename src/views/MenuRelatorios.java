package views;

import db.ClienteDAO;
import db.CorretorDAO;
import db.ImovelDAO;
import db.InteresseDAO;
import db.PropostaDAO;
import db.VisitaDAO;
import models.Cliente;
import models.Corretor;
import models.Imovel;
import models.Interesse;
import models.Proposta;
import models.Visita;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuRelatorios {
    private Scanner scanner;
    private ImovelDAO imovelDAO;
    private VisitaDAO visitaDAO;
    private PropostaDAO propostaDAO;
    private ClienteDAO clienteDAO;
    private CorretorDAO corretorDAO;
    private InteresseDAO interesseDAO; // ADICIONADO
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public MenuRelatorios() {
        this.scanner = new Scanner(System.in);
        this.imovelDAO = new ImovelDAO();
        this.visitaDAO = new VisitaDAO();
        this.propostaDAO = new PropostaDAO();
        this.clienteDAO = new ClienteDAO();
        this.corretorDAO = new CorretorDAO();
        this.interesseDAO = new InteresseDAO(); // ADICIONADO
    }

    public void exibir() {
        int opcao = -1;
        do {
            System.out.println("\n--- Menu de Relatórios ---");
            System.out.println("1. Listar Todos os Imóveis");
            System.out.println("2. Listar Todos os Clientes");
            System.out.println("3. Listar Todos os Corretores");
            System.out.println("4. Listar Todos os Interesses"); // ADICIONADO
            System.out.println("5. Listar Todas as Visitas");
            System.out.println("6. Listar Todas as Propostas");
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
                    listarImoveis();
                    break;
                case 2:
                    listarClientes();
                    break;
                case 3:
                    listarCorretores();
                    break;
                case 4:
                    listarInteresses(); // ADICIONADO
                    break;
                case 5:
                    listarVisitas();
                    break;
                case 6:
                    listarPropostas();
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

    private void listarImoveis() {
        System.out.println("\n--- Relatório de Imóveis ---");
        ArrayList<Imovel> imoveis = imovelDAO.listarTodos();
        if (imoveis.isEmpty()) {
            System.out.println("📋 Nenhum imóvel cadastrado.");
        } else {
            imoveis.forEach(imovel -> System.out.printf(
                "ID: %d | Endereço: %s | Tipo ID: %d | Status ID: %d\n",
                imovel.getId(),
                imovel.getEndereco(),
                imovel.getTipoImovelId(),
                imovel.getStatusImovelId()
            ));
        }
    }

    private void listarClientes() {
        System.out.println("\n--- Relatório de Clientes ---");
        ArrayList<Cliente> clientes = clienteDAO.listarTodos();
        if (clientes.isEmpty()) {
            System.out.println("📋 Nenhum cliente cadastrado.");
        } else {
            clientes.forEach(c -> System.out.printf(
                "ID: %d | Nome: %s | CPF: %s | Telefone: %s | Email: %s\n",
                c.getId(), c.getNome(), c.getCpf(), c.getTelefone(), c.getEmail()
            ));
        }
    }

    private void listarCorretores() {
        System.out.println("\n--- Relatório de Corretores ---");
        ArrayList<Corretor> corretores = corretorDAO.listarTodos();
        if (corretores.isEmpty()) {
            System.out.println("📋 Nenhum corretor cadastrado.");
        } else {
            corretores.forEach(c -> System.out.printf(
                "ID: %d | Nome: %s | CRECI: %s | CPF: %s | Telefone: %s | Email: %s\n",
                c.getId(), c.getNome(), c.getCreci(), c.getCpf(), c.getTelefone(), c.getEmail()
            ));
        }
    }

    private void listarInteresses() {
        System.out.println("\n--- Relatório de Interesses ---");
        ArrayList<Interesse> interesses = interesseDAO.listarTodos();
        if (interesses.isEmpty()) {
            System.out.println("📋 Nenhum interesse registrado.");
        } else {
            interesses.forEach(i -> System.out.printf(
                "ID: %d | Cliente ID: %d | Imóvel ID: %d | Data: %s\n",
                i.getId(), i.getClienteId(), i.getImovelId(), i.getDataInteresse().format(dateFormatter)
            ));
        }
    }

    private void listarVisitas() {
        System.out.println("\n--- Relatório de Visitas ---");
        ArrayList<Visita> visitas = visitaDAO.listarTodos();
        if (visitas.isEmpty()) {
            System.out.println("📋 Nenhuma visita agendada.");
        } else {
            visitas.forEach(visita -> System.out.printf(
                "ID: %d | Data: %s | Imóvel ID: %d | Cliente ID: %d | Corretor ID: %d\n",
                visita.getId(),
                visita.getDataVisita().format(dateFormatter),
                visita.getImovelId(),
                visita.getClienteId(),
                visita.getCorretorId()
            ));
        }
    }

    private void listarPropostas() {
        System.out.println("\n--- Relatório de Propostas ---");
        ArrayList<Proposta> propostas = propostaDAO.listarTodos();
        if (propostas.isEmpty()) {
            System.out.println("📋 Nenhuma proposta cadastrada.");
        } else {
            propostas.forEach(proposta -> System.out.printf(
                "ID: %d | Data: %s | Imóvel ID: %d | Cliente ID: %d\n",
                proposta.getId(),
                proposta.getDataProposta().format(dateFormatter),
                proposta.getImovelId(),
                proposta.getClienteId()
            ));
        }
    }
}
