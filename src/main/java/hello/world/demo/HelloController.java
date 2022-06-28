package hello.world.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private String[] tmp = new String[] {"Survey1", "Survey2", "Survey3"};
    private String[] tmp2 = new String[] {"Drink1", "Drink2", "Drink3"};

    @GetMapping("Surveys")
    public String showSurveys() {
        String s = "";
        for(String string: tmp) {
            s+= string + "\r\n";
        }
        return s;
    }

    @GetMapping("ServiceRequest")
    public String showServiceRequest() {
        String s = "";
        for(String string: tmp2) {
            s+= string + "\r\n";
        }
        return s;
    }
}
