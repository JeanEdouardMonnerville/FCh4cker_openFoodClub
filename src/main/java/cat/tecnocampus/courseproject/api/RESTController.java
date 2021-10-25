package cat.tecnocampus.courseproject.api;

import cat.tecnocampus.courseproject.application.Controller;
import cat.tecnocampus.courseproject.application.dtos.OrderDTO;
import cat.tecnocampus.courseproject.application.dtos.ProductDTO;

import java.util.List;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@Validated
@RestController
public class RESTController {
    Controller controller;

    public RESTController(Controller controller) {
        this.controller=controller;
    }
     
    @GetMapping("/api/Products")
    public List<ProductDTO> getProducts(){
        return controller.getAllProducts();
    }
    
    //@GetMapping("/User")
    //public CustomerDTO getCurrentCustomer(){
      //  return controller.getCustomerConnected();
    //}
    
    //@GetMapping("/User/{id}/Order")
   // public OrderDTO getCurrentOrderOfACurrentUser(@PathVariable String id){
     //   return controller.getCurrentOrderOfUser(id);
   // }
    
    
     @PostMapping("/api/Order/")
     public void addOrder(@RequestBody OrderDTO order ){
            controller.addOrder(order);
     }
     
     /*
     @Param quantity : indicate the quantity of the product added. If no value
     entered, we will considerate quantity=1
     */
     @PostMapping("/api/Order/Product/")
     public void addAProduct(@RequestBody OrderDTO order,
             @RequestBody ProductDTO product,
             @RequestBody int quantity){
         
         controller.addProductOnOrder(order, product, quantity);
     }
         

}
