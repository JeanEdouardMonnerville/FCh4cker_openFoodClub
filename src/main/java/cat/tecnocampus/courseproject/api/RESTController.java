package cat.tecnocampus.courseproject.api;

import cat.tecnocampus.courseproject.application.Controller;
import cat.tecnocampus.courseproject.application.dtos.CostumerDTO;
import cat.tecnocampus.courseproject.application.dtos.OrderDTO;
import cat.tecnocampus.courseproject.application.dtos.ProductDTO;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@Validated
@RestController("/api")
public class RESTController {
    Controller controller;
     
    @GetMapping("/Products")
    public List<ProductDTO> getProducts(){
        return controller.getAllProducts();
    }
    
    @GetMapping("/User")
    public CostumerDTO getCurrentCustomer(){
        return controller.getCustomerConnected();
    }
    
    @GetMapping("/User/{id}/Order")
    public OrderDTO getCurrentOrderOfACurrentUser(@PathVariable String id){
        return controller.getCurrentOrderOfUser(id);
    }
    
    
     //The id here is the id of a custommer
     @PostMapping("/Order/{id}")
     public void addOrder(@PathVariable String id){
            controller.addOrder(id);
     }
     
     /*
     @Param quantity : indicate the quantity of the product added. If no value
     entered, we will considerate quantity=1
     */
     @PostMapping("Order/{id_order}/Product/{id_product}/")
     public void addAProduct(@PathVariable String id_order,
             @PathVariable String id_product, @RequestParam(defaultValue ="1") int quantity){
         controller.addProductOnOrder(id_order,id_product,quantity);
     }
         

}
