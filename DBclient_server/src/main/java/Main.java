import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    private Connection getNewConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/zacet";
        String name = "postgres";
        String password = "228228";
        return DriverManager.getConnection(url, name, password);
    }

    public static void main(String[] args) {
      /*  System.out.println("Main");
        String url = "jdbc:postgresql://localhost:5432/zacet";
        String name = "postgres";
        String password = "228228";
        Flyway flyway = Flyway.configure().dataSource(url,name,password).load();
        flyway.migrate();
        Worker worker = new Worker();
        System.out.println(worker.getType());*/

    }
}
