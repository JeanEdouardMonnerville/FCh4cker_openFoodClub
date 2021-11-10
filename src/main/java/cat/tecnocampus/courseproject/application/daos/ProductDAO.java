package cat.tecnocampus.courseproject.application.daos;

import cat.tecnocampus.courseproject.application.dtos.ProductDTO;
import java.util.List;

public interface ProductDAO {
    
    public ProductDTO getById(String id);
    public List<ProductDTO> getAll();
    
}
