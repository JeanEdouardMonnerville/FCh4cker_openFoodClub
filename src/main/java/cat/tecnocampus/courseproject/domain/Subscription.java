package cat.tecnocampus.courseproject.domain;


public class Subscription {
    private int quantity;
    private Customer customer;
    private Product product;

    public Subscription(int quantity, Product product, Customer customer) {
        this.quantity = quantity;
        this.product = product;
        this.customer = customer;
    }
    
    public Subscription() { }

    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
