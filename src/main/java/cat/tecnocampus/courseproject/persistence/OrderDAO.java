package cat.tecnocampus.courseproject.persistence;

import cat.tecnocampus.courseproject.application.dtos.OrderDTO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.simpleflatmapper.jdbc.spring.ResultSetExtractorImpl;
import org.simpleflatmapper.jdbc.spring.RowMapperImpl;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDAO implements cat.tecnocampus.courseproject.application.daos.OrderDAO {

    JdbcTemplate jdbcTemplate;

    private String query_base = " SELECT o.id, o.creation_date, o.open, "
            + " s.id as subscriptions_id, s.quantity as subscriptions_quantity, "
            + " s.sub_date as subscriptions_sub_date, p.id as subscriptions_product_id, "
            + " c.id as subscriptions_customer_id "
            + " FROM t_Order o inner join Subscription s on o.Subscriptions = s.id "
            + " inner join product p on s.product = p.id "
            + " inner join customer c on s.customer = c.id ";
    
    /*
    private String query_base = "Select * FROM t_Order o inner join Subscription s on o.Subscriptions = s.id "
            + " inner join product p on s.product = p.id " 
            + " inner join customer c on s.customer = c.id" ;*/
    

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
        String query = query_base + " Where o.id=?";
        return jdbcTemplate.queryForObject(id, orderRowMapper, id);
    }

    @Override
    public List<OrderDTO> getAll() {
        return jdbcTemplate.query(query_base, ordersRowMapper);
    }

    @Override
    public void createOrder(List<Integer> subscriptions_id, LocalDateTime creation_time) {
        System.out.println("cat.tecnocampus.courseproject.persistence.OrderDAO.createOrder()");
        
        String query = " Insert into t_Order (id, subscriptions, creation_date,open)"
                + " values (?,?,?,?) ";
        for (int s_id : subscriptions_id) {
            String id = UUID.randomUUID().toString();
            jdbcTemplate.update(query, id, s_id, creation_time, true);
        }
    }

}
