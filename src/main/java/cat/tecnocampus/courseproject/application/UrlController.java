
package cat.tecnocampus.courseproject.application;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UrlController {
    
    @GetMapping(path= "/index")
    public String init(){
        return "products";
    }
}
