import db.ConnectionFactory;
import db.PopularDados;
import views.MenuPrincipal;

public class App {
    public static void main(String[] args) throws Exception {

        if (ConnectionFactory.getConnection() != null) {
            PopularDados.popularTudo();

            MenuPrincipal menu = new MenuPrincipal();
            menu.exibir();
        } else {
            System.err.println("❌ Não foi possível conectar ao banco de dados!");
            System.err.println("Verifique as configurações no arquivo .env");
        }
    }
}
