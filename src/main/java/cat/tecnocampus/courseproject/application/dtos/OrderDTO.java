package cat.tecnocampus.courseproject.application.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public class OrderDTO {

    private String id;
    private CustomerDTO customer;
    private List<SubscriptionDTO> order_details;
    private LocalDateTime creation_date;
    private boolean open;

    public OrderDTO() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public List<SubscriptionDTO> getOrder_details() {
        return order_details;
    }

    public void setOrder_details(List<SubscriptionDTO> order_details) {
        this.order_details = order_details;
    }

    public LocalDateTime getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(LocalDateTime creation_date) {
        this.creation_date = creation_date;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    //An order represents a preorder and users are able to change it until the 
    //next Friday at 23:45h, when the order will be closed.
    public boolean isModificationPossible() {
        LocalDateTime nextFriday = creation_date.plusDays(4).withHour(23).withMinute(45);
        return nextFriday.isBefore(LocalDateTime.now());
    }
    
    public void checkOpen(){
        this.open = isModificationPossible();
    }

}
