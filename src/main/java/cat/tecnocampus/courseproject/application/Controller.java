package cat.tecnocampus.courseproject.application;


import cat.tecnocampus.courseproject.application.dtos.CostumerDTO;
import cat.tecnocampus.courseproject.application.dtos.OrderDTO;
import cat.tecnocampus.courseproject.application.dtos.ProductDTO;
import java.util.List;
import org.springframework.stereotype.Component;



@Component
public class Controller {
    
    //TBD: get a list of all products
    public List<ProductDTO> getAllProducts() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //TBD: get the customer connected
    public CostumerDTO getCustomerConnected() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //TBD: get the order more recent of a customer
    public OrderDTO getCurrentOrderOfUser(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //TBD: add a new order for a customer
    public void addOrder(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //TBD: add an order detail linking an order and a product with a quantity
    public void addProductOnOrder(String id_order, String id_product, int quantity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
