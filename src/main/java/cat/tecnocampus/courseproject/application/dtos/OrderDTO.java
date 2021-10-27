package cat.tecnocampus.courseproject.application.dtos;


import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


public class OrderDTO {
    private String id_order;
    private CustomerDTO user;
    private LocalDate order_date;
    private List<SubscriptionDTO> orderDetail;

    public OrderDTO() {
       this.id_order = UUID.randomUUID().toString();
    }

    public String getId_order() {
        return id_order;
    }

    public CustomerDTO getUser() {
        return user;
    }

    public LocalDate getOrder_date() {
        return order_date;
    }

    public List<SubscriptionDTO> getOrderDetail() {
        return orderDetail;
    }

    public void setId_order(String id_order) {
        this.id_order = id_order;
    }

    public void setUser(CustomerDTO user) {
        this.user = user;
    }

    public void setOrder_date(LocalDate order_date) {
        this.order_date = order_date;
    }

    public void setOrderDetail(List<SubscriptionDTO> orderDetail) {
        this.orderDetail = orderDetail;
    }
    
}
