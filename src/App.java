import db.ConnectionFactory;

public class App {
    public static void main(String[] args) throws Exception {
        ConnectionFactory.getConnection();
    }
}
