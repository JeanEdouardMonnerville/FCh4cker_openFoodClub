package cat.tecnocampus.courseproject.application;

import cat.tecnocampus.courseproject.application.daos.CustomerDAO;
import cat.tecnocampus.courseproject.application.daos.SubscriptionDAO;
import cat.tecnocampus.courseproject.application.dtos.CustomerDTO;
import cat.tecnocampus.courseproject.application.dtos.SubscriptionDTO;
import cat.tecnocampus.courseproject.application.dtos.ProductDTO;
import cat.tecnocampus.courseproject.domain.Customer;
import cat.tecnocampus.courseproject.domain.Subscription;
import cat.tecnocampus.courseproject.domain.Product;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Controller {

    private final RestTemplate restTemplate;
    private static final String apiUrl = "https://productapi-1635325374837.azurewebsites.net";

    public Controller(RestTemplate restTemplate) {this.restTemplate = restTemplate;}

    @Scheduled(cron = "0 0 ? * MON")
    public double getNewPrice() {
        double newPrice = 0;
        var price = restTemplate.getForObject(apiUrl+"/api/v1/products/price", ProductDTO.class);
        for(int i=0;i<products.size();i++) {
            if(products.get(i).getId().equals(price.getId_product())) {
                newPrice = price.getPrice();
            }
        }
        return newPrice;
    }


    private HashMap<String, Product> products;
    private CustomerDAO customerDAO;
    private SubscriptionDAO subscriptionDAO;


    public void setProducts(HashMap<String, Product> products) {
        this.products = products;
    }




    public List<ProductDTO> getAllProducts() {
        return products.values().stream().map(this::productToProductDTO).collect(Collectors.toList());
    }

    public ProductDTO getOneProduct(String id) {
        if (products.containsKey(id)) {
            return productToProductDTO(products.get(id));
        }
        return null;
    }


    public List<SubscriptionDTO> getAllSubscription() {
        /*
        List<SubscriptionDTO> listSubscription = new ArrayList<>();
        for (Subscription s : subscriptions) {
            listSubscription.add(subscriptionToSubscriptionDTO(s));
        }
        return listSubscription;*/
        return subscriptionDAO.getSubscriptions();
    }
    
    public CustomerDTO getCustomerByName(String name){
        return customerDAO.getCustomerBYName(name);
    }

    public CustomerDTO getCustomer(String id){
        return customerDAO.getCustomerById(id);
    }
    
    public List<CustomerDTO> getCustomers(){
        return customerDAO.getAllCustomer();
    }

    public void addProductOnSubscription(String customerId, String productId, int quantity) {
        /*SubscriptionDTO subscriptionDTO = new SubscriptionDTO();

        Product product = products.get(productId);
        Customer customer = customerDTOToCustomer(customerDAO.getCustomerById(customerId));
        ProductDTO productDTO = productToProductDTO(product);
        CustomerDTO customerDTO = customerToCustomerDTO(customer);

        subscriptionDTO.setProduct(productDTO);
        subscriptionDTO.setCustomer(customerDTO);
        subscriptionDTO.setQuantity(quantity);
        subscriptionDTO.setDate(LocalDate.now());

        subscriptions.add(subscriptionDTOToSubscription(subscriptionDTO));*/
        subscriptionDAO.addSubscription(customerId, productId, quantity);
        
    }

    /**
     * **************************
     */
    private ProductDTO productToProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId_product(product.getId());
        productDTO.setName(product.getName());
        productDTO.setCategory(product.getCategory());
        productDTO.setPrice(product.getPrice());
        productDTO.setMeasure(product.getMeasure_unit());
        productDTO.setSuppliers(product.getProvider());
        productDTO.setClient_tax(product.getVat_type());
        productDTO.setImage(product.getImage());
        return productDTO;
    }

    private Product productDTOToProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId_product());
        product.setName(productDTO.getName());
        product.setCategory(productDTO.getCategory());
        product.setPrice(productDTO.getPrice());
        product.setMeasure_unit(productDTO.getMeasure());
        product.setProvider(productDTO.getSuppliers());
        product.setVat_type(productDTO.getClient_tax());
        product.setImage(productDTO.getImage());
        return product;
    }

    private SubscriptionDTO subscriptionToSubscriptionDTO(Subscription subscription) {
        SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
        subscriptionDTO.setQuantity(subscription.getQuantity());
        subscriptionDTO.setDate(subscription.getDate());
        subscriptionDTO.setProduct(productToProductDTO(subscription.getProduct()));
        subscriptionDTO.setCustomer(customerToCustomerDTO(subscription.getCustomer()));
        return subscriptionDTO;
    }
    private Subscription subscriptionDTOToSubscription(SubscriptionDTO subscriptionDTO){
        Subscription subscription = new Subscription();
        subscription.setQuantity(subscriptionDTO.getQuantity());
        subscription.setDate(subscriptionDTO.getDate());
        subscription.setProduct(productDTOToProduct(subscriptionDTO.getProduct()));
        subscription.setCustomer(customerDTOToCustomer(subscriptionDTO.getCustomer()));
        return subscription;
    }

    private CustomerDTO customerToCustomerDTO(Customer user) {
        CustomerDTO customerdto = new CustomerDTO();
        customerdto.setEmail(user.getEmail());
        customerdto.setName(user.getName());
        customerdto.setId(user.getId());
        customerdto.setSecondName(user.getSecondName());
        return customerdto;
    }
    public Customer customerDTOToCustomer(CustomerDTO customerdto){
        Customer customer = new Customer();
        customer.setEmail(customerdto.getEmail());
        customer.setName(customerdto.getName());
        customer.setId(customerdto.getId());     
        customer.setSecondName(customerdto.getSecondName());
        return customer;
    }
}
