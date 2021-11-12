package cat.tecnocampus.courseproject.application.daos;

import cat.tecnocampus.courseproject.application.dtos.SubscriptionDTO;
import java.util.List;


public interface SubscriptionDAO {
    
    public List<SubscriptionDTO> getSubscription(String customerId);
    public List<SubscriptionDTO> getSubscriptions();
    public SubscriptionDTO getOneByID(int id);
    
    public void addSubscription(String customerId, String productId, int quantity);
    
    public void deleteSubscription(String customerId, String productId);
    
}
