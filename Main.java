package Marketplace;

import Marketplace.config.Database;
import Marketplace.views.MainMenu;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("Marketplace")
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        MainMenu mainMenu = context.getBean(MainMenu.class);
        mainMenu.run();
    }

    @Bean
    Database database() {
        Database database = new Database("marketplaceDB", "root", "", "localhost", "3306");
        database.setup();
        return database;
    }
}