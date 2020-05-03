import Entities.Edition;
import Repository.AllReaderRepository;
import Services.Impl.EditionServiceImpl;
import UserInterface.InterfaceController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;

@Configuration
@EnableAutoConfiguration
public class Main {



    private Connection getNewConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/zacet";
        String name = "postgres";
        String password = "228228";
        return DriverManager.getConnection(url, name, password);
    }


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class);
        //Global global = new Global();
       // AllReaderRepository repository = context.getBean(AllReaderRepository.class);
       // repository.findAll();
    }

}
