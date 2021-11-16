package cat.tecnocampus.courseproject.persistence;

import cat.tecnocampus.courseproject.application.dtos.OrderDTO;
import cat.tecnocampus.courseproject.application.exceptions.OrderDoesNotExistException;
import cat.tecnocampus.courseproject.application.exceptions.UserDoesNotExistException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.simpleflatmapper.jdbc.spring.ResultSetExtractorImpl;
import org.simpleflatmapper.jdbc.spring.RowMapperImpl;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDAO implements cat.tecnocampus.courseproject.application.daos.OrderDAO {

    JdbcTemplate jdbcTemplate;

    private String query_base = " SELECT o.id, o.creation_date, o.open,o.quantity, "
            + "c.id as customer_id, c.name as "
            + " customer_name, c.secondName as customer_secondName, c.email as "
            + " customer_email, p.id as product_id, p.name as product_name, "
            + " p.price as product_price, p.MEASURE_UNIT as product_measure_unit, "
            + " p.provider as product_provider, p.vat_type as product_vat_type, "
            + " p.category as product_category, p.initial_date as product_initial_date, "
            + " p.day_of_week as product_day_of_week,p.num_of_periods product_num_of_periods, "
            + " p.period as product_period, p.image as product_image "
            + " FROM t_order o inner join customer c on c.id=o.customer "
            + " inner join product p on p.id=o.product";

    public OrderDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    ResultSetExtractorImpl<OrderDTO> ordersRowMapper
            = JdbcTemplateMapperFactory
                    .newInstance()
                    .addKeys("id")
                    .newResultSetExtractor(OrderDTO.class);

    RowMapperImpl<OrderDTO> orderRowMapper
            = JdbcTemplateMapperFactory
                    .newInstance()
                    .addKeys("id")
                    .newRowMapper(OrderDTO.class);

    @Override
    public OrderDTO getOneByID(String id) {
        try {
            String query = query_base + " Where o.id=?";
            return jdbcTemplate.queryForObject(query, orderRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            throw new OrderDoesNotExistException(id);
        }
    }

    @Override
    public List<OrderDTO> getAll() {
        return jdbcTemplate.query(query_base, ordersRowMapper);
    }
    
    @Override
    public List<OrderDTO> getOrdersByCustomerId(String customer_id){
        String query = query_base + " where o.customer = ?";
        try{
            return jdbcTemplate.query(query,ordersRowMapper,customer_id);
        }
        catch(EmptyResultDataAccessException e){
            throw new UserDoesNotExistException(customer_id);
        }
    }
    
    @Override
    public void createOrder(String product_id, String customer_id, LocalDateTime creation_date, int quantity) {

        String query = " Insert into t_Order (id, product, customer, creation_date, quantity, open)"
                + " values (?,?,?,?,?,?) ";
        String id = UUID.randomUUID().toString();
        jdbcTemplate.update(query, id, product_id, customer_id, creation_date, quantity, true);
    }

    @Override
    public void updateOrder(String order_id, int new_quantity) {
        String query = "UPDATE T_Order SET quantity=? where id=?";
        jdbcTemplate.update(query, new_quantity,order_id);
    }

    @Override
    public void deleteOrder(String order_id) {
        try {
            String query = "DELETE from t_order where id=?";
            jdbcTemplate.update(query, order_id);
        } catch (EmptyResultDataAccessException e) {
            throw new OrderDoesNotExistException(order_id);
        }
    }

}
