package cat.tecnocampus.courseproject.persistence;

import cat.tecnocampus.courseproject.application.dtos.SubscriptionDTO;
import java.time.LocalDate;
import java.util.List;
import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.simpleflatmapper.jdbc.spring.ResultSetExtractorImpl;
import org.simpleflatmapper.jdbc.spring.RowMapperImpl;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class SubscriptionDAO implements cat.tecnocampus.courseproject.application.daos.SubscriptionDAO {
//TBD : les produits proviennent d'une api, donc soit télécharge toutes les données de l'API (lourd)
    //soit on remplace produit en domaine et DTO par produit_id et la subscription ne comportera 
    //que l'id du produit et ensuite dans le front on reconstruit la subscription qu'on voulait 
    //de base avec les données de l'api.
    JdbcTemplate jdbcTemplate;
    

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
                    .addKeys("customer,product")
                    .newRowMapper(SubscriptionDTO.class);

    @Override
    public List<SubscriptionDTO> getSubscription(String customerId) {
        try{
        String query = "Select s.quantity, s.sub_date,c.id as customer_id, "
                + " c.name as customer_name, "
                + " c.secondname as customer_secondname, "
                + " c.email as customer_email from Subscription "
                + " inner join Customer c on c.id=s.customer "
                + " where s.customer=? ";
        return jdbcTemplate.query(query, subscriptionsRowMapper, customerId);}
        catch(EmptyResultDataAccessException e){
            return null;//TBD
        }
    }

    @Override
    public List<SubscriptionDTO> getSubscriptions() {
        String query = "Select * from Subscription";
        return jdbcTemplate.query(query, subscriptionsAllRowMapper);
    }

    @Override
    public void addSubscription(String customerId, String productId, int quantity) {
        if (subscriptionNotExists(customerId,productId)) {
            String query = "INSERT INTO Subscription values (?,?,?,?)";
            jdbcTemplate.update(query, quantity, LocalDate.now(), customerId, productId);
        } else {
            String query = "UPDATE Subscription SET quantity=?,sub_date=? where customer=? and product=?";
            jdbcTemplate.update(query, quantity, LocalDate.now(), customerId);
        }
    }

    @Override
    public void deleteSubscription(String customerId, String productId) {
        try{
        String query = "DELETE FROM Subscription where customer=? and product=?";
        jdbcTemplate.update(query, customerId, productId);}
        catch(EmptyResultDataAccessException e){
            //TBD
        }
    }

    private boolean subscriptionNotExists(String productId, String customerId) {
        String query = "Select * from subscription where product=? and customer=?";
        SubscriptionDTO subscription = new SubscriptionDTO();
        subscription =  jdbcTemplate.queryForObject(query, subscriptionRowMapper,productId,customerId);
        if(subscription==null){
            return true;
        }
        return false;
    }
}
