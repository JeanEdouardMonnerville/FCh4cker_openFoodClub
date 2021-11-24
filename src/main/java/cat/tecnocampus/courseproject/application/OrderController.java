package cat.tecnocampus.courseproject.application;

import cat.tecnocampus.courseproject.application.daos.OrderDAO;
import cat.tecnocampus.courseproject.application.dtos.OrderDTO;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class OrderController {

    private OrderDAO orderDao;

    public OrderController(OrderDAO orderDao) {
        this.orderDao = orderDao;
    }

    public List<OrderDTO> getAllOrders() {
        return orderDao.getAll();
    }

    public List<OrderDTO> getOrderForCustomerById(String customer_id) {
        return orderDao.getOrdersByCustomerId(customer_id);
    }

    public List<OrderDTO> getOrderForCustomerByName(String customer_name) {
        return orderDao.getOrderByCustomerName(customer_name);
    }

    public OrderDTO getOrderById(String id_order) {
        return orderDao.getOneByID(id_order);
    }

    public void updateQuantityOneOrder(String id_order, int quantity, String customer_name) {
        OrderDTO order = getOrderById(id_order);
        if (order.getCustomer().getName().equals(customer_name)) {
            order.checkOpen();
            if (order.isOpen()) {
                orderDao.updateOrder(id_order, quantity);
            }
        }
    }

    public void deleteOrder(String id_order, String customer_name) {
        OrderDTO order = getOrderById(id_order);
        if (order.getCustomer().getName().equals(customer_name)) {
            orderDao.deleteOrder(id_order);
        }
    }

    public void updateQuantityOneOrderForAdmin(String id_order, int quantity) {
        orderDao.updateOrder(id_order, quantity);
    }

}
