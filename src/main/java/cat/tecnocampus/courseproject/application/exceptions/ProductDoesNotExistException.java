package cat.tecnocampus.courseproject.application.exceptions;

public class ProductDoesNotExistException extends RuntimeException{
    public ProductDoesNotExistException(String product) {
        super("The product " + product + " does not exist");
    }
}
