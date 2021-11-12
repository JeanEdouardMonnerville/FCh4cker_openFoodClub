package cat.tecnocampus.courseproject.domain;

import java.time.LocalDate;

public class Subscription {
    private int id; 
    private int quantity;
    private LocalDate date;
    private Customer customer;
    private Product product;

    public Subscription(int id, int quantity, LocalDate date, Product product, Customer customer) {
        this.id = id;
        this.quantity = quantity;
        this.date = date;
        this.product = product;
        this.customer = customer;
    }
    
    
    public Subscription() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }

    public Customer getCustomer() {return customer;}

    public LocalDate getDate() {return date;}

    public void setQuantity(int quantity) {this.quantity = quantity;}

    public void setProduct(Product product) {this.product = product;}

    public void setCustomer(Customer customer) {this.customer = customer;}

    public void setDate(LocalDate date) {this.date = date;}
}
