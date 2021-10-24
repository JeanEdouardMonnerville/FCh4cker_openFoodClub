package cat.tecnocampus.courseproject.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Order {

    private String id_order;
    private Customer user;
    private LocalDate order_date;
    private List<OrderDetail> orderDetail;

    public Order( Customer user, LocalDate order_date, List<OrderDetail> orderDetail) {
        this.id_order = UUID.randomUUID().toString();
        this.user = user;
        this.order_date = order_date;
        this.orderDetail = orderDetail;
    }
    
    public Order(){
    }

    public void setId_order(String id_order) {
        this.id_order = id_order;
    }

    public void setUser(Customer user) {
        this.user = user;
    }

    public void setOrder_date(LocalDate order_date) {
        this.order_date = order_date;
    }

    public void setOrderDetail(List<OrderDetail> orderDetail) {
        this.orderDetail = orderDetail;
    }

    public String getId_order() {
        return id_order;
    }

    public Customer getUser() {
        return user;
    }

    public LocalDate getOrder_date() {
        return order_date;
    }

    public List<OrderDetail> getOrderDetail() {
        return orderDetail;
    }

}
