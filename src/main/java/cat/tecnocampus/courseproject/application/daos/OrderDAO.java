package cat.tecnocampus.courseproject.application.daos;

import cat.tecnocampus.courseproject.application.dtos.OrderDTO;
import java.time.LocalDateTime;
import java.util.List;


public interface OrderDAO {
    
    public OrderDTO getOneByID(String id);
    public List<OrderDTO> getAll();
    
    public void createOrder(List<Integer> subscriptions_id,LocalDateTime creation_time);
    
}
