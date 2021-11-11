package cat.tecnocampus.courseproject.persistence;

import cat.tecnocampus.courseproject.application.dtos.ProductDTO;
import java.util.List;

import cat.tecnocampus.courseproject.application.exceptions.ProductDoesNotExistException;
import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.simpleflatmapper.jdbc.spring.ResultSetExtractorImpl;
import org.simpleflatmapper.jdbc.spring.RowMapperImpl;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDAO implements cat.tecnocampus.courseproject.application.daos.ProductDAO {

    JdbcTemplate jdbctemplate;

    public ProductDAO(JdbcTemplate jdbctemplate) {
        this.jdbctemplate = jdbctemplate;
    }

    ResultSetExtractorImpl<ProductDTO> productsRowMapper
            = JdbcTemplateMapperFactory
                    .newInstance()
                    .addKeys("id")
                    .newResultSetExtractor(ProductDTO.class);

    RowMapperImpl<ProductDTO> productRowMapper
            = JdbcTemplateMapperFactory
                    .newInstance()
                    .addKeys("id")
                    .newRowMapper(ProductDTO.class);

    @Override
    public ProductDTO getById(String id) {
        try {
            final String query = "Select * from Product where id=?";
            return jdbctemplate.queryForObject(id, productRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            throw new ProductDoesNotExistException(id);
        }

    }

    @Override
    public List<ProductDTO> getAll() {
        final String query = "Select * From product";
        return jdbctemplate.query(query, productsRowMapper);
    }

}
