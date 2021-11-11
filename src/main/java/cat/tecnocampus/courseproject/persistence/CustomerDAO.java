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
        final String query = "Select * from customer where id=?";
        try{
        return jdbcTemplate.queryForObject(query,customerRowMapper,id );}
        catch(EmptyResultDataAccessException e){
            throw new UserDoesNotExistException(id);
        }
    }

    @Override
    public CustomerDTO getCustomerBYName(String name) {
        final String query = "Select * from customer where name=?";
        try{
        return jdbcTemplate.queryForObject(query,customerRowMapper,name );
        }catch(EmptyResultDataAccessException e){
            throw new UserDoesNotExistException(name);
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomer() {
      final String query= "Select * from customer";
      return jdbcTemplate.query(query,customersRowMapper);
    }

}
