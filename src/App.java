import db.ConnectionFactory;
import db.PopularDados;
import views.MenuPrincipal;

public class App {
    public static void main(String[] args) throws Exception {
        // Testar conexão com o banco
        if (ConnectionFactory.getConnection() != null) {
            // Popular dados iniciais (tipos e status de imóveis)
            PopularDados.popularTudo();
            
            // Iniciar o menu principal
            MenuPrincipal menu = new MenuPrincipal();
            menu.exibir();
        } else {
            System.err.println("❌ Não foi possível conectar ao banco de dados!");
            System.err.println("Verifique as configurações no arquivo .env");
        }
    }
}
