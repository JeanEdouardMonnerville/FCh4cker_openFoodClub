package cat.tecnocampus.courseproject.application.exceptions;

public class UserDoesNotExistException extends RuntimeException {
    public UserDoesNotExistException(String name) {
        super("User" + name + " doesn't exist");
    }
}
