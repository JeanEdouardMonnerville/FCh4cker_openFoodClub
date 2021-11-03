package cat.tecnocampus.courseproject.persistence;

import cat.tecnocampus.courseproject.application.dtos.ProductDTO;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Repository
public class ProductDAO implements cat.tecnocampus.courseproject.application.daos.ProductDAO {

    private JDbCTemplate
    List<ProductDTO> getAllProducts();

    ProductDTO getProductById(String id);

}
