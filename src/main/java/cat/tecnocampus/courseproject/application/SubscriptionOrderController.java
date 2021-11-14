package cat.tecnocampus.courseproject.application;

import cat.tecnocampus.courseproject.application.daos.CustomerDAO;
import cat.tecnocampus.courseproject.application.daos.OrderDAO;
import cat.tecnocampus.courseproject.application.daos.SubscriptionDAO;
import cat.tecnocampus.courseproject.application.dtos.CustomerDTO;
import cat.tecnocampus.courseproject.application.dtos.SubscriptionDTO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class SubscriptionOrderController {
    private SubscriptionDAO subscriptiondao;
    private OrderDAO orderDao;
    private CustomerDAO customerDao;

    public SubscriptionOrderController(SubscriptionDAO subscriptiondao, OrderDAO orderDao) {
        this.subscriptiondao = subscriptiondao;
        this.orderDao = orderDao;
    }
    
    //TBD : Periodicity
    public void creationOfAllOrders(){
        List<CustomerDTO> customers = customerDao.getAllCustomer();
        for(CustomerDTO c : customers){
            creationOfOneOrder(c.getId());
        }
    }
    
    private void creationOfOneOrder(String customer_id){
        List<SubscriptionDTO> subscriptions = subscriptiondao.getSubscription(customer_id);
        List<Integer> subscriptions_id = new ArrayList<>();
        
        for(SubscriptionDTO s : subscriptions){
            subscriptions_id.add(s.getId());
        }
        orderDao.createOrder(subscriptions_id, LocalDateTime.now());
    }
    
    
}
