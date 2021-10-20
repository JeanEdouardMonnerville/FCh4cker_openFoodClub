package cat.tecnocampus.courseproject.application.dtos;

import java.util.UUID;

public class ProductDTO {
    private String id_product;
    private String name;
    private String category;
    private int price;
    private String measure;
    private String suppliers;
    private String client_tax;
    private String image;

    public ProductDTO() {
         this.id_product = UUID.randomUUID().toString();
    }

    public String getId_product() {
        return id_product;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }

    public String getMeasure() {
        return measure;
    }

    public String getSuppliers() {
        return suppliers;
    }

    public String getClient_tax() {
        return client_tax;
    }

    public String getImage() {
        return image;
    }

    public void setId_product(String id_product) {
        this.id_product = id_product;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public void setSuppliers(String suppliers) {
        this.suppliers = suppliers;
    }

    public void setClient_tax(String client_tax) {
        this.client_tax = client_tax;
    }

    public void setImage(String image) {
        this.image = image;
    }

  
}
