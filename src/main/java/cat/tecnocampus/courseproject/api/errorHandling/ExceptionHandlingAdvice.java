package cat.tecnocampus.courseproject.api.errorHandling;

import cat.tecnocampus.courseproject.application.exceptions.UserDoesNotExistException;
import cat.tecnocampus.courseproject.application.exceptions.OrderDoesNotExistException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlingAdvice {

    @ResponseBody
   
    @ResponseStatus(HttpStatus.CONFLICT)
    String objectNotFoundHandler(Exception ex) {
        return ex.getMessage();
    }

    @ExceptionHandler({UserDoesNotExistException.class, OrderDoesNotExistException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String objectAlreadyExists(Exception exception) {
        return exception.getMessage();
    }

}
