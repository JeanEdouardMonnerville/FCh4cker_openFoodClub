package cat.tecnocampus.courseproject.application;

import cat.tecnocampus.courseproject.application.dtos.CustomerDTO;
import cat.tecnocampus.courseproject.application.dtos.SubscriptionDTO;
import cat.tecnocampus.courseproject.application.dtos.ProductDTO;
import cat.tecnocampus.courseproject.domain.Customer;
import cat.tecnocampus.courseproject.domain.Subscription;
import cat.tecnocampus.courseproject.domain.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class Controller {

    private HashMap<String, Product> products;
    private HashMap<String, Customer> customers;
    private ArrayList<Subscription> subscriptions;

    public void setOrderDetails(ArrayList<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public void setProducts(HashMap<String, Product> products) {
        this.products = products;
    }

    public void setCustomers(HashMap<String, Customer> customers) {
        this.customers = customers;
    }

    //TBD: get a list of all products
    public List<ProductDTO> getAllProducts() {
        return products.values().stream().map(this::productToProductDTO).collect(Collectors.toList());
    }

    public ProductDTO getOneProduct(String id) {
        if (products.containsKey(id)) {
            return productToProductDTO(products.get(id));
        }
        return null;
    }

    //TBD: get the customer connected
    //public CustomerDTO getCustomerConnected() {
    //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}
    //TBD: get the order more recent of a customer
    // public OrderDTO getCurrentOrderOfUser(String id) {
    //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}

    //TBD: add an order detail linking an order and a product with a quantity
    public void addProductOnSubscription(String customerId, String productId, int quantity) {
        SubscriptionDTO subscriptionDTO = new SubscriptionDTO();

        Product product = products.get(productId);
        Customer customer = customers.get(customerId);
        ProductDTO productDTO = productToProductDTO(product);
        CustomerDTO customerDTO = customerToCustomerDTO(customer);

        subscriptionDTO.setProduct(productDTO);
        subscriptionDTO.setCustomer(customerDTO);
        subscriptionDTO.setQuantity(quantity);

        subscriptions.add(subscriptionDTOToSubscription(subscriptionDTO));
    }

    /**
     * **************************
     */
    private ProductDTO productToProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId_product(product.getId_product());
        productDTO.setName(product.getName());
        productDTO.setCategory(product.getCategory());
        productDTO.setPrice(product.getPrice());
        productDTO.setMeasure(product.getMeasure());
        productDTO.setSuppliers(product.getSuppliers());
        productDTO.setClient_tax(product.getClient_tax());
        productDTO.setImage(product.getImage());
        return productDTO;
    }

    private Product productDTOToProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setId_product(productDTO.getId_product());
        product.setName(productDTO.getName());
        product.setCategory(productDTO.getCategory());
        product.setPrice(productDTO.getPrice());
        product.setMeasure(productDTO.getMeasure());
        product.setSuppliers(productDTO.getSuppliers());
        product.setClient_tax(productDTO.getClient_tax());
        product.setImage(productDTO.getImage());
        return product;
    }

    private SubscriptionDTO subscriptionToSubscriptionDTO(Subscription subscription) {
        SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
        subscriptionDTO.setQuantity(subscription.getQuantity());
        subscriptionDTO.setProduct(productToProductDTO(subscription.getProduct()));
        subscriptionDTO.setCustomer(customerToCustomerDTO(subscription.getCustomer()));
        return subscriptionDTO;
    }
    private Subscription subscriptionDTOToSubscription(SubscriptionDTO subscriptionDTO){
        Subscription subscription = new Subscription();
        subscription.setQuantity(subscriptionDTO.getQuantity());
        subscription.setProduct(productDTOToProduct(subscriptionDTO.getProduct()));
        subscription.setCustomer(customerDTOToCustomer(subscriptionDTO.getCustomer()));
        return subscription;
    }

    private CustomerDTO customerToCustomerDTO(Customer user) {
        CustomerDTO customerdto = new CustomerDTO();
        customerdto.setEmail(user.getEmail());
        customerdto.setName(user.getName());
        customerdto.setId(user.getId());
        customerdto.setPassword(user.getPassword());
        customerdto.setRole(user.getRole());
        customerdto.setSecondName(user.getSecondName());
        return customerdto;
    }
    private Customer customerDTOToCustomer(CustomerDTO customerdto){
        Customer customer = new Customer();
        customer.setEmail(customerdto.getEmail());
        customer.setName(customerdto.getName());
        customer.setId(customerdto.getId());
        customer.setPassword(customerdto.getPassword());
        customer.setRole(customerdto.getRole());
        customer.setSecondName(customerdto.getSecondName());
        return customer;
    }
}
