package cat.tecnocampus.courseproject.persistence;

import cat.tecnocampus.courseproject.application.dtos.CustomerDTO;
import java.util.List;

import cat.tecnocampus.courseproject.application.exceptions.UserDoesNotExistException;
import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.simpleflatmapper.jdbc.spring.ResultSetExtractorImpl;
import org.simpleflatmapper.jdbc.spring.RowMapperImpl;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAO implements cat.tecnocampus.courseproject.application.daos.CustomerDAO {

    JdbcTemplate jdbcTemplate;

    public CustomerDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    ResultSetExtractorImpl<CustomerDTO> customersRowMapper
            = JdbcTemplateMapperFactory
                    .newInstance()
                    .addKeys("id")
                    .newResultSetExtractor(CustomerDTO.class);

    RowMapperImpl<CustomerDTO> customerRowMapper
            = JdbcTemplateMapperFactory
                    .newInstance()
                    .addKeys("id")
                    .newRowMapper(CustomerDTO.class);

    @Override
    public CustomerDTO getCustomerById(String id) {
        final String query = "Select id,name,secondName,email,role as roles from customer"
                + " inner join authorities on authorities.username=name where id=?";
        try{
        List<CustomerDTO> result = jdbcTemplate.query(query,customerRowMapper,id );
        return result.get(0);
        }
        catch(EmptyResultDataAccessException e){
            throw new UserDoesNotExistException(id);
        }
    }

    @Override
    public CustomerDTO getCustomerBYName(String name) {
        final String query = "Select id,name,secondName,email,role as roles from customer"
                + " inner join authorities on authorities.username=name where name=?";
        try{
        List<CustomerDTO> result = jdbcTemplate.query(query, customersRowMapper,name);
        return result.get(0);
        }catch(EmptyResultDataAccessException e){
            throw new UserDoesNotExistException(name);
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomer() {
      final String query= "Select id,name,secondName,email,role as roles from customer"
              + " inner join authorities on authorities.username=name";
      return jdbcTemplate.query(query,customersRowMapper);
    }

}
