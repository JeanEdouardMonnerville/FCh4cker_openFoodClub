package cat.tecnocampus.courseproject.application;

import cat.tecnocampus.courseproject.application.dtos.CustomerDTO;
import cat.tecnocampus.courseproject.application.dtos.OrderDTO;
import cat.tecnocampus.courseproject.application.dtos.OrderDetailDTO;
import cat.tecnocampus.courseproject.application.dtos.ProductDTO;
import cat.tecnocampus.courseproject.domain.Customer;
import cat.tecnocampus.courseproject.domain.Order;
import cat.tecnocampus.courseproject.domain.OrderDetail;
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
    private HashMap<String, Order> orders;
    private ArrayList<OrderDetail> orderDetails;

    public void setOrderDetails(ArrayList<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public void setProducts(HashMap<String, Product> products) {
        this.products = products;
    }

    public void setCustomers(HashMap<String, Customer> customers) {
        this.customers = customers;
    }

    public void setOrders(HashMap<String, Order> orders) {
        this.orders = orders;
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

    //TBD: get a list of all orders
    public List<OrderDTO> getAllOrders() {
        return orders.values().stream().map(this::orderToOrderDTO).collect(Collectors.toList());
    }

    //TBD: get the customer connected
    //public CustomerDTO getCustomerConnected() {
    //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}
    //TBD: get the order more recent of a customer
    // public OrderDTO getCurrentOrderOfUser(String id) {
    //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}
    
    //TBD: add a new order for a customer
    public void addOrder(OrderDTO orderdto) {
        Order order = orderDTOToOrder(orderdto);
        orders.put(order.getId_order(), order);
    }

    //TBD: add an order detail linking an order and a product with a quantity
    public void addProductOnOrder(OrderDTO order, ProductDTO product, int quantity) {
        OrderDetailDTO orderDetail = new OrderDetailDTO();
        
        orderDetail.setOrder(order);
        orderDetail.setProduct(product);
        orderDetail.setQuantity(quantity);
        
        orderDetails.add(orderDetailsDTOToorderDetails(orderDetail));
        orders.get(order.getId_order()).setOrderDetail(orderDetails);
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

    private OrderDTO orderToOrderDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId_order(order.getId_order());
        orderDTO.setUser(customerToCustomerDTO(order.getUser()));
        orderDTO.setOrder_date(order.getOrder_date());

        List<OrderDetailDTO> orderDetails = new ArrayList<>();
        for (OrderDetail od : order.getOrderDetail()) {
            orderDetails.add(orderDetailsToOrderDetailsDTO(od));
        }
        orderDTO.setOrderDetail(orderDetails);
        return orderDTO;
    }

    private Order orderDTOToOrder(OrderDTO orderdto) {
        Order order = new Order();
        
        order.setId_order(orderdto.getId_order());
        order.setUser(customerDTOToCustomer(orderdto.getUser()));
        order.setOrder_date(orderdto.getOrder_date());

        List<OrderDetail> orderDetails = new ArrayList<>();
        for (OrderDetailDTO od : orderdto.getOrderDetail()) {
            orderDetails.add(orderDetailsDTOToorderDetails(od));
        }
        order.setOrderDetail(orderDetails);
        return order;
    }

    private OrderDetailDTO orderDetailsToOrderDetailsDTO(OrderDetail orderDetail) {
        OrderDetailDTO orderDetailsDTO = new OrderDetailDTO();
        orderDetailsDTO.setQuantity(orderDetail.getQuantity());
        orderDetailsDTO.setOrder(orderToOrderDTO(orderDetail.getOrder()));
        orderDetailsDTO.setProduct(productToProductDTO(orderDetail.getProduct()));
        return orderDetailsDTO;
    }
    private OrderDetail orderDetailsDTOToorderDetails(OrderDetailDTO orderDetailDto){
       OrderDetail orderDetail = new OrderDetail();
       
        orderDetail.setQuantity(orderDetailDto.getQuantity());
        orderDetail.setOrder(orderDTOToOrder(orderDetailDto.getOrder()));
        orderDetail.setProduct(productDTOToProduct(orderDetailDto.getProduct()));
        return orderDetail;
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
