package cat.tecnocampus.courseproject.application.dtos;

public class OrderDetailDTO {

    private int quantity;
    private OrderDTO order;
    private ProductDTO product;

    public OrderDetailDTO() {
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public OrderDTO getOrder() {
        return order;
    }

    public ProductDTO getProduct() {
        return product;
    }
    
}
