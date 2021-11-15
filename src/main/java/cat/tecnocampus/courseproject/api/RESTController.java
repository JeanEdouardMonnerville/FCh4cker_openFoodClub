package cat.tecnocampus.courseproject.api;

import cat.tecnocampus.courseproject.application.Controller;
import cat.tecnocampus.courseproject.application.SubscriptionOrderController;
import cat.tecnocampus.courseproject.application.dtos.CustomerDTO;
import cat.tecnocampus.courseproject.application.dtos.OrderDTO;
import cat.tecnocampus.courseproject.application.dtos.ProductDTO;

import java.util.List;

import cat.tecnocampus.courseproject.application.dtos.SubscriptionDTO;
import cat.tecnocampus.courseproject.domain.Subscription;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
public class RESTController {

    Controller basicController;
    SubscriptionOrderController SOcontroller;

    public RESTController(Controller controller, SubscriptionOrderController SOcontroller) {
        this.basicController = controller;
        this.SOcontroller = SOcontroller;
    }

    @GetMapping("/api/products")
    public List<ProductDTO> getProducts() {
        return basicController.getAllProducts();
    }

    @GetMapping("/api/subscriptions")
    public List<SubscriptionDTO> getSubscriptions() {
        return basicController.getAllSubscription();
    }
    
    /**
     @Param quantity : indicate the quantity of the product added. If no value
     entered, we will considerate quantity=1
     */
    @PostMapping("/api/subscription")
    public void addAProduct(@RequestParam String customerId,
            @RequestParam String productId,
            @RequestParam int quantity) {
        basicController.addProductOnSubscription(customerId, productId, quantity);
    }
    
    @GetMapping("api/orders/{id_customer}")
    public List<OrderDTO> getMyOrders(@PathVariable String id_customer){
        SOcontroller.creationOfAllOrders();
        return SOcontroller.getOrderForCustomer(id_customer);
        
    }
        

}
