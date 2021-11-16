package cat.tecnocampus.courseproject.application;

import cat.tecnocampus.courseproject.application.daos.CustomerDAO;
import cat.tecnocampus.courseproject.application.daos.OrderDAO;
import cat.tecnocampus.courseproject.application.daos.SubscriptionDAO;
import cat.tecnocampus.courseproject.application.dtos.CustomerDTO;
import cat.tecnocampus.courseproject.application.dtos.SubscriptionDTO;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionOrderController {

    private SubscriptionDAO subscriptiondao;
    private OrderDAO orderDao;
    private CustomerDAO customerDao;

    public SubscriptionOrderController(SubscriptionDAO subscriptiondao, OrderDAO orderDao, CustomerDAO customerDao) {
        this.subscriptiondao = subscriptiondao;
        this.orderDao = orderDao;
        this.customerDao = customerDao;
    }

    @Scheduled(cron = "0 0 2 * MON")// Every monday at 2h00am . 
    public void creationOfAllOrders() {
        List<CustomerDTO> customers = customerDao.getAllCustomer();
        for (CustomerDTO c : customers) {
            creationOfOneOrder(c.getId());
        }
    }

    /**
     *
     * @param customer_id id of a customer.
     */
    private void creationOfOneOrder(String customer_id) {
        List<SubscriptionDTO> subscriptions = subscriptiondao.getSubscription(customer_id);
        for (SubscriptionDTO s : subscriptions) {
            if (checkPeriodicity(s.getProduct().getInitial_date(), s.getProduct().getNum_of_periods(), s.getProduct().getPeriod())) {
                orderDao.createOrder(s.getProduct().getId(), customer_id, LocalDateTime.now(), s.getQuantity());
            }
        }
    }

    /**
     *
     * @param initial_date the initiale date contained by product informations.
     * @param num_of_periods the periodicy.
     * @param period can be equal to month, week or year.
     * @return True if the product can be added to a order and false if not.
     */
    private boolean checkPeriodicity(LocalDate initial_date, int num_of_periods, String period) {
        //I get the monday date of the current week
        LocalDate today;
        if (!initial_date.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
            today = LocalDate.now().with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
        } else {
            today = LocalDate.now();
        }

        //today = LocalDate.of(2021, 11, 29); //Test today to see if it works
        LocalDate InitialeMonday;
        if (!initial_date.getDayOfWeek().equals(DayOfWeek.MONDAY)) //I get the monday date of the initial week
        {
            InitialeMonday = initial_date.with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
        } else {
            InitialeMonday = initial_date;
        }
        if (initial_date.isBefore(today)) {
            switch (period) {
                case "Week":
                    //First of all, we calculate the number of days between now and the monday date of the initial_week

                    //I calculate the number of days between the current monday date and the initial monday date
                    int weekBetweenInitialAndNow = Period.between(InitialeMonday, today).getDays() / 7;

                    //If it equal the difference is equal to 0, that means that the current monday is equal to the initial monday
                    if (weekBetweenInitialAndNow != 0) //If the remainder of Euclidean division is equal to 0, we return true
                    {
                        return weekBetweenInitialAndNow % num_of_periods == 0;
                    }

                case "Month":
                    //Same strategy for month
                    int monthBetweenInitialAndNow = Period.between(InitialeMonday, today).getMonths();
                    if (monthBetweenInitialAndNow != 0) {
                        return monthBetweenInitialAndNow % num_of_periods == 0;
                    }

                case "Year":
                    //Same strategy for year
                    int yearsBetweenInitialAndNow = Period.between(InitialeMonday, today).getYears();
                    if (yearsBetweenInitialAndNow != 0) {
                        return yearsBetweenInitialAndNow % num_of_periods == 0;
                    }

            }
        }

        return false;
    }

}
