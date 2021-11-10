package cat.tecnocampus.courseproject;

import cat.tecnocampus.courseproject.application.Controller;
import cat.tecnocampus.courseproject.domain.Customer;
import cat.tecnocampus.courseproject.domain.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import cat.tecnocampus.courseproject.domain.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebApplication implements CommandLineRunner {
    private Controller controller;

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

    @Autowired
    public void setController(Controller controller) {
        this.controller = controller;
    }
    
    @Override
    public void run(String... strings) throws Exception {

        ArrayList<Subscription> subscriptions = new ArrayList<>();
 
        controller.addProductOnSubscription("1", "101", 5);
    }

}
