package cat.tecnocampus.courseproject.application;


import cat.tecnocampus.courseproject.application.dtos.CostumerDTO;
import cat.tecnocampus.courseproject.application.dtos.OrderDTO;
import cat.tecnocampus.courseproject.application.dtos.ProductDTO;
import java.util.List;
import org.springframework.stereotype.Component;



@Component
public class Controller {

    private HashMap<String, Product> products;
    private HashMap<String, Customer> customers;
    private HashMap<String, Order> orders;
    private HashMap<String, OrderDetails> orderDetails;

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
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ProductDTO getOneProduct(String id) {
        if (products.containsKey(id)) {
            return productToProductDTO(students.get(id));
        }
    }

    //TBD: get a list of all orders
    public List<OrderDTO> getAllOrders() {
        return orders.values().stream().map(this::orderToOrderDTO).collect(Collectors.toList());
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //TBD: get the customer connected
    public CostumerDTO getCustomerConnected() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //TBD: get the order more recent of a customer
    public OrderDTO getCurrentOrderOfUser(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //TBD: add a new order for a customer
    public void addOrder(Order order, Costumer costumer) {
        Order order = orderToOrderDTO(order);
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //TBD: add an order detail linking an order and a product with a quantity
    public void addProductOnOrder(Order order, Product product, int quantity) {
        OrderDetails orderDetail = new OrderDetails();
        orderDetail.setOrder(order);
        orderDetail.setProduct(product);
        orderDetail.setQuantity(quantity);
        orderDetails.put(orderDetail);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /*****************************/

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
        orderDTO.setUser(order.getUser());
        orderDTO.setOrder_date(order.getOrder_date());
        orderDTO.setOrderDetails(order.getOrderDetails());
        return orderDTO;
    }

    private OrderDetailsDTO orderDetailsToOrderDetailsDTO(OrderDetails orderDetails) {
        OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();
        orderDetailsDTO.setQuantity(orderDetails.getQuantity());
        orderDetailsDTO.setOrder(orderDetails.getOrder());
        orderDetailsDTO.setProduct(orderDetails.getProduct());
        return orderDetailsDTO;
    }
}
