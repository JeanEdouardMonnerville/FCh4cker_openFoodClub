package cat.tecnocampus.courseproject.api;

import cat.tecnocampus.courseproject.application.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@Validated
@RestController
public class RESTController {
    Controller roomController;

}
