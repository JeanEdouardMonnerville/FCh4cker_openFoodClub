package cat.tecnocampus.courseproject;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

    public HashMap<String, Product> products = new HashMap<String>();

    public HashMap<String, Customer> customers = new HashMap<String>();

    var customer = new Customer(id: "1", name: "Pepe", secondName: "Gimenez",
    email:"pgimenez@tecnocampus.cat", password:"12345678", role:"admin");
    var product = new Product(id_product:"1", name:"peer", category:"fruit", price:"2",
            measure:"kilo", suppliers: "Juan", client_tax:"0,42", image:"imageNotFound");
    var product = new Product(id_product:"2", name:"tomato", category:"legume", price:"1",
    measure:"kilo", suppliers: "Juan", client_tax:"0,21", image:"imageNotFound");

}
