package cat.tecnocampus.courseproject.api;

import cat.tecnocampus.courseproject.application.Controller;
import cat.tecnocampus.courseproject.application.OrderController;
import cat.tecnocampus.courseproject.application.SubscriptionOrderController;
import cat.tecnocampus.courseproject.application.dtos.CustomerDTO;
import cat.tecnocampus.courseproject.application.dtos.OrderDTO;
import cat.tecnocampus.courseproject.application.dtos.ProductDTO;

import java.security.Principal;
import java.util.List;

import cat.tecnocampus.courseproject.application.dtos.SubscriptionDTO;
import cat.tecnocampus.courseproject.domain.Subscription;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
public class RESTController {

    Controller basicController;
    SubscriptionOrderController SOcontroller;
    OrderController orderController;

    public RESTController(Controller controller, SubscriptionOrderController SOcontroller, OrderController orderController) {
        this.basicController = controller;
        this.SOcontroller = SOcontroller;
        this.orderController = orderController;
    }

    @GetMapping("/api/customers/all")
    public List<CustomerDTO> getCustomers() {
        return basicController.getCustomers();
    }

    @GetMapping("/api/customers/{id_customer}")
    public CustomerDTO getCustomerById(@PathVariable String id_customer) {
        return basicController.getCustomer(id_customer);
    }

    @GetMapping("/api/customers/me")
    public CustomerDTO getCurrentCustomer(Principal principal) {
        return basicController.getCustomerByName(principal.getName());
    }

    @GetMapping("/api/products")
    public List<ProductDTO> getProducts() {
        return basicController.getAllProducts();
    }

    @GetMapping("/api/subscriptions")
    public List<SubscriptionDTO> getSubscriptions() {
        return basicController.getAllSubscription();
    }

    @GetMapping("/api/subscriptions/me")
    public List<SubscriptionDTO> getMySubscriptions(Principal principal) {
        return basicController.getSubscriptionsForCustomer(principal.getName());
    }

    /**
     * @param customerId id of the customer connected
     * @param productId id of the product concerned
     * @param quantity quantity post by the user
     * @Param quantity : indicate the quantity of the product added. If no value
     * entered, we will considerate quantity=1
     */
    @PostMapping("/api/subscription")
    public void addAProduct(@RequestParam String customerId,
            @RequestParam String productId,
            @RequestParam int quantity) {
        basicController.addProductOnSubscription(customerId, productId, quantity);
    }

    @GetMapping("api/customers/orders/me")
    public List<OrderDTO> getMyOrders(Principal principal) {
        //SOcontroller.creationOfAllOrders();//TEST LINE CODE
        return orderController.getOrderForCustomerByName(principal.getName());
    }

    @GetMapping("api/orders/{id_order}")
    public OrderDTO getOrderByID(@PathVariable String id_order) {
        return orderController.getOrderById(id_order);
    }

    @PostMapping("api/orders/update/{id_order}")
    public void updateOrderQuantity(@PathVariable String id_order, @RequestParam int quantity, Principal principal) {
        orderController.updateQuantityOneOrder(id_order, quantity, principal.getName());
    }

    @PostMapping("api/orders/admin/update/{id_order}")
    public void updateOrderQuantityForAdmin(@PathVariable String id_order, @RequestParam int quantity) {
        orderController.updateQuantityOneOrderForAdmin(id_order, quantity);
    }

    @GetMapping("api/orders/delete/{id_order}")
    public void deleteAnOrder(@PathVariable String id_order, Principal principal) {
        orderController.deleteOrder(id_order, principal.getName());
    }

    @GetMapping("api/orders/all")
    public List<OrderDTO> getAllOrders() {
        return orderController.getAllOrders();
    }

}
