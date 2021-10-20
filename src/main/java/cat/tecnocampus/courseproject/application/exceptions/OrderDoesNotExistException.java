package cat.tecnocampus.courseproject.application.exceptions;

public class OrderDoesNotExistException extends RuntimeException {
    public OrderDoesNotExistException(String order_Id) {
        super("Order " + order_Id + " doesn't exist");
    }
}
