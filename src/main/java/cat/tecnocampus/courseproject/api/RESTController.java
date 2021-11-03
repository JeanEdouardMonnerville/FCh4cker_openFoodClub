package cat.tecnocampus.courseproject.api;

import cat.tecnocampus.courseproject.application.Controller;
import cat.tecnocampus.courseproject.application.daos.ProductDAO;
import cat.tecnocampus.courseproject.application.dtos.CustomerDTO;
import cat.tecnocampus.courseproject.application.dtos.OrderDTO;
import cat.tecnocampus.courseproject.application.dtos.ProductDTO;

import java.util.List;

import cat.tecnocampus.courseproject.application.dtos.SubscriptionDTO;
import cat.tecnocampus.courseproject.domain.Subscription;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/v1")
@Validated
@RestController
public class RESTController {
    Controller controller;
    private ProductDAO productDAO;

    public RESTController(Controller controller) {
        this.controller=controller;
    }
     
    @GetMapping("/api/products")
    public List<ProductDTO> getProducts(){
        return controller.getAllProducts();
    }

    @GetMapping("/api/subscriptions")
    public List<SubscriptionDTO> getSubscriptions(){
        return controller.getAllSubscription();
    }

    @GetMapping("/products")
    public List<ProductDTO> getAllProducts() {
        return productDAO.getAllProducts();
    }

    //@GetMapping("/User")
    //public CustomerDTO getCurrentCustomer(){
      //  return controller.getCustomerConnected();
    //}
    
    //@GetMapping("/User/{id}/Order")
   // public OrderDTO getCurrentOrderOfACurrentUser(@PathVariable String id){
     //   return controller.getCurrentOrderOfUser(id);
   // }
    
/*
     @PostMapping("/api/order/")
     public void addOrder(@RequestBody OrderDTO order ){
            controller.addOrder(order);
     }
*/
     /*
     @Param quantity : indicate the quantity of the product added. If no value
     entered, we will considerate quantity=1
     */
     @PostMapping("/api/{getId}/{orderId}/{productId}/")
     public void addAProduct(@RequestBody String customerId, @RequestBody String productId, @RequestBody int quantity){
         controller.addProductOnSubscription(customerId, productId, quantity);
     }
         

}
