package Marketplace;

import Marketplace.config.Database;
import Marketplace.views.Menus;
import Marketplace.views.TerminalView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("Marketplace")
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        Menus mainMenu = context.getBean(TerminalView.class);
        mainMenu.run();
    }

    @Bean
    Database database() {
        Database database = new Database("marketplace", "root", "", "localhost", "3306");
        database.setup();
        return database;
    }
}