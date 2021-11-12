package cat.tecnocampus.courseproject.persistence;

import cat.tecnocampus.courseproject.application.dtos.SubscriptionDTO;
import java.time.LocalDate;
import java.util.List;

import cat.tecnocampus.courseproject.application.exceptions.SubscriptionDoesNotExistException;
import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.simpleflatmapper.jdbc.spring.ResultSetExtractorImpl;
import org.simpleflatmapper.jdbc.spring.RowMapperImpl;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SubscriptionDAO implements cat.tecnocampus.courseproject.application.daos.SubscriptionDAO {

    private JdbcTemplate jdbcTemplate;
    private String query_base = "SELECT s.quantity, s.sub_date, c.id as customer_id, c.name as "
            + " customer_name, c.secondName as customer_secondName, c.email as "
            + " customer_email, p.id as product_id, p.name as product_name, "
            + " p.price as product_price, p.MEASURE_UNIT as product_measure_unit, "
            + " p.provider as product_provider, p.vat_type as product_vat_type, "
            + " p.category as product_category, p.initial_date as product_initial_date, "
            + " p.day_of_week as product_day_of_week,p.num_of_periods product_num_of_periods, "
            + " p.period as product_period, p.image as product_image "
            + " FROM Subscription s inner join customer c on c.id=s.customer "
            + " inner join product p on p.id=s.product";

    public SubscriptionDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    ResultSetExtractorImpl<SubscriptionDTO> subscriptionsAllRowMapper
            = JdbcTemplateMapperFactory
                    .newInstance()
                    .newResultSetExtractor(SubscriptionDTO.class);

    ResultSetExtractorImpl<SubscriptionDTO> subscriptionsRowMapper
            = JdbcTemplateMapperFactory
                    .newInstance()
                    .addKeys("customer")
                    .newResultSetExtractor(SubscriptionDTO.class);

    RowMapperImpl<SubscriptionDTO> subscriptionRowMapper
            = JdbcTemplateMapperFactory
                    .newInstance()
                    //.addKeys("customer,product")
                    .newRowMapper(SubscriptionDTO.class);

    @Override
    public List<SubscriptionDTO> getSubscription(String customerId) {
        try {
            String query = query_base
                    + " where s.customer=? ";
            return jdbcTemplate.query(query, subscriptionsRowMapper, customerId);
        } catch (EmptyResultDataAccessException e) {
            throw new SubscriptionDoesNotExistException(customerId);
        }
    }

    @Override
    public List<SubscriptionDTO> getSubscriptions() {
        return jdbcTemplate.query(query_base, subscriptionsAllRowMapper);
    }

    @Override
    public void addSubscription(String customerId, String productId, int quantity) {
        System.out.println(subscriptionNotExists(productId, customerId));
        if (subscriptionNotExists(productId, customerId)) {
            String query = "INSERT INTO Subscription(quantity,sub_date,customer,product) values (?,?,?,?)";
            jdbcTemplate.update(query, quantity, LocalDate.now(), customerId, productId);
            System.out.println("INSERT################");
        } else {
            String query = "UPDATE Subscription SET quantity=?,sub_date=? where customer=? and product=?";
            jdbcTemplate.update(query, quantity, LocalDate.now(), customerId,productId);
            System.out.println("UPDATE################");
        }
    }

    @Override
    public void deleteSubscription(String customerId, String productId) {
        try {
            String query = "DELETE FROM Subscription where customer=? and product=?";
            jdbcTemplate.update(query, customerId, productId);
        } catch (EmptyResultDataAccessException e) {
            throw new SubscriptionDoesNotExistException(customerId, productId);
        }
    }

    private boolean subscriptionNotExists(String productId, String customerId) {
        boolean result = true;
        List<SubscriptionDTO> subsciptions = jdbcTemplate.query(query_base, subscriptionsAllRowMapper);
        for(SubscriptionDTO s : subsciptions){
            if(s.getCustomer().getId().equals(customerId) && s.getProduct().getId().equals(productId) ){
                result=false;
            }
        }
        return result;
    }

}
