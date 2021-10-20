package cat.tecnocampus.courseproject.api.errorHandling;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ErrorValidationHandlingAdvice {
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    String onConstraintValidationException(
            ConstraintViolationException e) {
        ValidationErrorResponse error = new ValidationErrorResponse();
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            error.setViolation(
                    new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
        }
        return error.toString();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    String onMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        ValidationErrorResponse error = new ValidationErrorResponse();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            error.setViolation(
                    new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return error.toString();
    }

    public class ValidationErrorResponse {

        private List<Violation> violations = new ArrayList<>();

        public ValidationErrorResponse() {
        }

        public List<Violation> getViolations() {
            return violations;
        }

        public void setViolation(Violation violation) {
            violations.add(violation);
        }

        @Override
        public String toString() {
            String result = "ValidationErrorResponse{";
            for (Violation e : violations) {
                result += e;
            }
            result += "}";

            return result;
        }
    }

    public class Violation {

        private final String fieldName;
        private final String message;

        public Violation(String fieldName, String message) {
            this.fieldName = fieldName;
            this.message = message;
        }

        @Override
        public String toString() {
            return "Violation{" +
                    "fieldName='" + fieldName + '\'' +
                    ", message='" + message + '\'' +
                    '}';
        }
    }
}
