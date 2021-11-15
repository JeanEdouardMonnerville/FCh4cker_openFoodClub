package cat.tecnocampus.courseproject.application.exceptions;


public class OrderDoesNotExistException extends RuntimeException{

    public OrderDoesNotExistException(String id) {
        super("The order whith the id " + id + " does not exist");
    }
    
}
