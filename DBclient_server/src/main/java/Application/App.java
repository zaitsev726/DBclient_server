package Application;

import org.flywaydb.core.Flyway;

public class App {
    private static final String url = "jdbc:postgresql://localhost:54325/postgres";
    private static final String user = "postgres";
    private static final String password = "mysecretpassword";
    public static void main(String[] args){
        Flyway flyway = Flyway.configure().dataSource(url, user, password).load();
        flyway.migrate();
        Global global = new Global();
      
    }
}
