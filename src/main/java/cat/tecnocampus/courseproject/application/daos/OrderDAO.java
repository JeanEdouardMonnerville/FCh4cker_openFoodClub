package cat.tecnocampus.courseproject.application.daos;

import cat.tecnocampus.courseproject.application.dtos.OrderDTO;
import java.time.LocalDateTime;
import java.util.List;


public interface OrderDAO {
    
    public OrderDTO getOneByID(String id);
    public List<OrderDTO> getAll();
    public List<OrderDTO> getOrdersByCustomerId(String customer_id);
    public List<OrderDTO> getOrderByCustomerName(String name);
    
    public void createOrder(String product_id, String customer_id, LocalDateTime creation_date,int quantity);
    
    public void updateOrder(String order_id, int new_quantity);
    public void deleteOrder(String order_id);
    
}
