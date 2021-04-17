package pl.sda.covidvavapp.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    //adres waszej appki - localhost:8080/hello
    @GetMapping("/hello/{langCode}")
    public String helloWorldInfo(@PathVariable String langCode) {
        switch (langCode.toUpperCase()) {
            case "EN":
                return "Hello world";
            case "ES":
                return "Hola!";
            case "PL":
                return "Witaj świecie!";
            default:
                return "Uknown language code";
        }
    }
}
