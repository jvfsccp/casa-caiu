package views;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuPrincipal {
    private Scanner scanner;
    private MenuImoveis menuImoveis;
    private MenuClientes menuClientes;
    private MenuCorretores menuCorretores;
    private MenuVisitas menuVisitas;
    private MenuPropostas menuPropostas;
    private MenuRelatorios menuRelatorios;

    public MenuPrincipal() {
        this.scanner = new Scanner(System.in);
        this.menuImoveis = new MenuImoveis();
        this.menuClientes = new MenuClientes();
        this.menuCorretores = new MenuCorretores();
        this.menuVisitas = new MenuVisitas();
        this.menuPropostas = new MenuPropostas();
        this.menuRelatorios = new MenuRelatorios();
    }

    public void exibir() {
        int opcao = -1;
        do {
            System.out.println("\n╔════════════════════════════════════╗");
            System.out.println("║    SISTEMA IMOBILIÁRIO CASA CAIU   ║");
            System.out.println("╚════════════════════════════════════╝");
            System.out.println("1. Gerenciar Imóveis");
            System.out.println("2. Gerenciar Clientes");
            System.out.println("3. Gerenciar Corretores");
            System.out.println("4. Gerenciar Visitas");
            System.out.println("5. Gerenciar Propostas");
            System.out.println("6. Emitir Relatórios");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("❌ Erro: Por favor, digite apenas números.");
                opcao = -1; // Reseta a opção para continuar no loop
            } finally {
                scanner.nextLine(); // Limpa o buffer do scanner
            }

            switch (opcao) {
                case 1:
                    menuImoveis.exibir();
                    break;
                case 2:
                    menuClientes.exibir();
                    break;
                case 3:
                    menuCorretores.exibir();
                    break;
                case 4:
                    menuVisitas.exibir();
                    break;
                case 5:
                    menuPropostas.exibir();
                    break;
                case 6:
                    menuRelatorios.exibir();
                    break;
                case 0:
                    System.out.println("\n👋 Encerrando o sistema...");
                    System.out.println("Obrigado por usar o Sistema Casa Caiu!");
                    break;
                case -1:
                    // Não faz nada, apenas continua o loop para nova entrada
                    break;
                default:
                    System.out.println("❌ Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
        
        scanner.close();
    }
}
