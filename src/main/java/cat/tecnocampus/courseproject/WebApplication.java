package cat.tecnocampus.courseproject;

import cat.tecnocampus.courseproject.application.Controller;
import cat.tecnocampus.courseproject.domain.Customer;
import cat.tecnocampus.courseproject.domain.Product;
import cat.tecnocampus.courseproject.domain.Role;
import java.util.HashMap;
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
        HashMap<String, Product> products = new HashMap<>();
        HashMap<String, Customer> customers = new HashMap<>();
        
        Customer customer = new Customer("Pepe", "Gimenez", "pgimenez@tecnocampus.cat", "12345678", Role.USER);
        customer.setId("1");
        
        Product product = new Product("peer", "fruit", 2, "kilo", "Juan", "0,42", "https://images.hermie.com/images/articles/large/plantenfiche-pyrus-communis-conference-conference-peer-15515.jpg");
        Product product1 = new Product("tomato", "legume", 1, "kilo", "Juan", "0,21", "https://www.sementesfeltrin.com.br/_uploads/produtofoto/produtofoto_561_3673_orig.jpg");
        product.setId_product("101");
        product1.setId_product("102");
        
        products.put(product.getId_product(), product);
        products.put(product1.getId_product(), product);
        
        customers.put(customer.getId(), customer);
        
        controller.setCustomers(customers);
        controller.setProducts(products);

    }

}