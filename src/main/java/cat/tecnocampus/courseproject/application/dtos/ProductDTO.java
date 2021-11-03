package cat.tecnocampus.courseproject.application.dtos;

import java.time.LocalDate;
import java.util.UUID;

public class ProductDTO {
    private String id;
    private String name;
    private double price;
    private String measure_unit;
    private String provider;
    private String vat_type;
    private String category;
    private LocalDate initial_date;
    private String day_of_week;
    private int num_of_periods;
    private String period;
    private String image;

    public ProductDTO() {
         this.id = UUID.randomUUID().toString();
    }

    public String getId_product() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public String getMeasure() {
        return measure_unit;
    }

    public String getSuppliers() {
        return provider;
    }

    public String getClient_tax() {
        return vat_type;
    }

    public String getImage() {
        return image;
    }

    public void setId_product(String id_product) {
        this.id = id_product;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setMeasure(String measure) {
        this.measure_unit = measure;
    }

    public void setSuppliers(String provider) {
        this.provider = provider;
    }

    public void setClient_tax(String vat_type) {
        this.vat_type= vat_type;
    }

    public void setImage(String image) {
        this.image = image;
    }

  
}
