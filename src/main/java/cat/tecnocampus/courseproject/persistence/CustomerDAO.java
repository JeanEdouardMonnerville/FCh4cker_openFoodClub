package cat.tecnocampus.courseproject.persistence;

import cat.tecnocampus.courseproject.application.dtos.CustomerDTO;
import java.util.List;
import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.simpleflatmapper.jdbc.spring.ResultSetExtractorImpl;
import org.simpleflatmapper.jdbc.spring.RowMapperImpl;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

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
            return null;//TBD 
        }
    }

    @Override
    public CustomerDTO getCustomerBYName(String name) {
        final String query = "Select * from customer where name=?";
        try{
        return jdbcTemplate.queryForObject(query,customerRowMapper,name );
        }catch(EmptyResultDataAccessException e){
            return null;//TBD
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomer() {
      final String query= "Select * from customer";
      return jdbcTemplate.query(query,customersRowMapper);
    }

}
