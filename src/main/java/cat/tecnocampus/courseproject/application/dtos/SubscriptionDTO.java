package cat.tecnocampus.courseproject.application.dtos;

public class SubscriptionDTO {

    private int quantity;
    private CustomerDTO customer;
    private ProductDTO product;

    public SubscriptionDTO() {}

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public ProductDTO getProduct() {
        return product;
    }
    
}
