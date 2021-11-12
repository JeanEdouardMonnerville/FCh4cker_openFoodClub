package cat.tecnocampus.courseproject.application.dtos;

import java.time.LocalDate;
import java.util.UUID;

public class SubscriptionDTO {

    private int id;
    private int quantity;
    private LocalDate sub_date;
    private CustomerDTO customer;
    private ProductDTO product;

    public SubscriptionDTO() {
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public void setSub_date(LocalDate sub_date) { this.sub_date = sub_date; }

    public int getQuantity() {
        return quantity;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public LocalDate getSub_date() { return sub_date; }

}
