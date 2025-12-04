package views;

import java.util.Scanner;

public class MenuPrincipal {
    private Scanner scanner;
    private MenuImoveis menuImoveis;
    private MenuClientes menuClientes;
    private MenuCorretores menuCorretores;

    public MenuPrincipal() {
        this.scanner = new Scanner(System.in);
        this.menuImoveis = new MenuImoveis();
        this.menuClientes = new MenuClientes();
        this.menuCorretores = new MenuCorretores();
    }

    public void exibir() {
        int opcao;
        do {
            System.out.println("\n╔════════════════════════════════════╗");
            System.out.println("║    SISTEMA IMOBILIÁRIO CASA CAIU   ║");
            System.out.println("╚════════════════════════════════════╝");
            System.out.println("1. Gerenciar Imóveis");
            System.out.println("2. Gerenciar Clientes");
            System.out.println("3. Gerenciar Corretores");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

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
                case 0:
                    System.out.println("\n👋 Encerrando o sistema...");
                    System.out.println("Obrigado por usar o Sistema Casa Caiu!");
                    break;
                default:
                    System.out.println("❌ Opção inválida!");
            }
        } while (opcao != 0);
        
        scanner.close();
    }
}
