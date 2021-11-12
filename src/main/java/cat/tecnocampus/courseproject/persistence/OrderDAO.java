package cat.tecnocampus.courseproject.persistence;

import cat.tecnocampus.courseproject.application.dtos.OrderDTO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.simpleflatmapper.jdbc.spring.ResultSetExtractorImpl;
import org.simpleflatmapper.jdbc.spring.RowMapperImpl;
import org.springframework.jdbc.core.JdbcTemplate;

public class OrderDAO implements cat.tecnocampus.courseproject.application.daos.OrderDAO {

    JdbcTemplate jdbcTemplate;

    private String query_base = " SELECT o.id, o.creation_date, o.open "
            + " s.id as orderDetail_id, s.quantity as orderDetail_quantity, "
            + " s.sub_date as orderDetail_sub_date, s.product as orderDetail_product, "
            + " s.customer as orderDetail_customer "
            + " FROM Order o inner join Subscription s on o.order_details = s.id ";

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
        String query = query_base + " Where id=?";
        return jdbcTemplate.queryForObject(id, orderRowMapper, id);
    }

    @Override
    public List<OrderDTO> getAll() {
        return jdbcTemplate.query(query_base, ordersRowMapper);
    }

    @Override
    public void createOrder(List<Integer> subscriptions_id, LocalDateTime creation_time) {
        String query = " Insert into Order (id, order_details, creation_date,open)"
                + " values (?,?,?,?) ";
        for (int s_id : subscriptions_id) {
            String id = UUID.randomUUID().toString();
            jdbcTemplate.update(query, id, s_id, creation_time, true);
        }
    }

}
