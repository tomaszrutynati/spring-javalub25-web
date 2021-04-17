package pl.sda.covidvavapp.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.sda.covidvavapp.api.model.User;

@RestController
public class HelloWorldController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldController.class);

    @GetMapping("/hello/{langCode}")
    public String helloWorldInfo(@PathVariable String langCode, @RequestParam String name) {
        String greetings;
        switch (langCode.toUpperCase()) {
            case "EN":
                greetings = "Hello world";
                break;
            case "ES":
                greetings = "Hola!";
                break;
            case "PL":
                greetings = "Witaj świecie!";
                break;
            default:
                return "Uknown language code";
        }

        return greetings + " " + name;
    }

    @PostMapping("/user")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createUser(@RequestBody User user) {
        LOGGER.info("First name " + user.getFirstName());
        LOGGER.info("Last name " + user.getLastName());
        LOGGER.info("City " + user.getCity());
    }

}
