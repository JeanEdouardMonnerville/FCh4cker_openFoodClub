package cat.tecnocampus.courseproject.application.daos;

import cat.tecnocampus.courseproject.application.dtos.ProductDTO;

import java.util.List;

public interface ProductDAO {
    List<ProductDTO> getAllProducts();

    ProductDTO getProductById(String id);

   // List<ProductPriceDTO> getAllProductPrices();

   // ProductPriceDTO getProductPriceById(String id);
}
