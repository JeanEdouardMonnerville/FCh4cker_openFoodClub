package cat.tecnocampus.courseproject.application.dtos.productApi;

import java.util.List;


public class ProductResponseList {
    private List<ProductResponseUnit> products;

    public ProductResponseList(List<ProductResponseUnit> products) {
        this.products = products;
    }

    public ProductResponseList() {
    }

    
    
    
    
    
    public List<ProductResponseUnit> getProducts() {
        return products;
    }

    public void setProducts(List<ProductResponseUnit> products) {
        this.products = products;
    }
    
    
    
}
