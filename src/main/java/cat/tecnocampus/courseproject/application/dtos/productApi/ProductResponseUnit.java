package cat.tecnocampus.courseproject.application.dtos.productApi;


public class ProductResponseUnit {
    private String id;
    private long price;

    public ProductResponseUnit(String id, long price) {
        this.id = id;
        this.price = price;
    }

    public ProductResponseUnit() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
    
}
