package cat.tecnocampus.courseproject.application.daos;



import cat.tecnocampus.courseproject.application.dtos.CustomerDTO;
import java.util.List;

public interface CustomerDAO {

    public CustomerDTO getCustomerById(String id);
    public CustomerDTO getCustomerBYName(String name);
    
    public List<CustomerDTO> getAllCustomer();
    
    
}
