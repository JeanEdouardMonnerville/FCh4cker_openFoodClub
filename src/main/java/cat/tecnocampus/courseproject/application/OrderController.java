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

    public List<OrderDTO> getOrderForCustomer(String customer_id) {
        return orderDao.getOrdersByCustomerId(customer_id);
    }

    public OrderDTO getOrderById(String id_order) {
        return orderDao.getOneByID(id_order);
    }

    public void updateQuantityOneOrder(String id_order, int quantity) {
        OrderDTO order = getOrderById(id_order);
        order.checkOpen();
        if (order.isOpen()) {
            orderDao.updateOrder(id_order, quantity);
        }
    }

    public void deleteOrder(String id_order) {
        orderDao.deleteOrder(id_order);
    }

}
