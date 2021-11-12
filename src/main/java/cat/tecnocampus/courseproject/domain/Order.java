package cat.tecnocampus.courseproject.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public class Order {

    private String id;
    private Customer customer;
    private List<Subscription> order_details;
    private LocalDate creation_date;
    private LocalDateTime creation_hour;
    private boolean open;

    public Order(Customer customer, List<Subscription> order_details, LocalDate creation_date, boolean closed) {
        this.id = UUID.randomUUID().toString();
        this.customer = customer;
        this.order_details = order_details;
        this.creation_date = creation_date;
        this.open = closed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Subscription> getOrder_details() {
        return order_details;
    }

    public void setOrder_details(List<Subscription> order_details) {
        this.order_details = order_details;
    }

    public LocalDate getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(LocalDate creation_date) {
        this.creation_date = creation_date;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

}
