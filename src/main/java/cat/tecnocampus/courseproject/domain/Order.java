package cat.tecnocampus.courseproject.domain;

import java.time.LocalDateTime;

import java.util.UUID;

public class Order {

    private String id;
    private Product product;
    private Customer customer;
    private LocalDateTime creation_date;
    private int quantity;
    private boolean open;

    public Order(Product product, Customer customer, LocalDateTime creation_date, int quantity, boolean open ) {
        this.id = UUID.randomUUID().toString();
        this.product = product;
        this.customer = customer;
        this.creation_date = creation_date;
        this.quantity = quantity;
        this.open = open;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(LocalDateTime creation_date) {
        this.creation_date = creation_date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }


}
