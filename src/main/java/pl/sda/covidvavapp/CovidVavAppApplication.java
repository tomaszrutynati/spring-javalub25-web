package pl.sda.covidvavapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import pl.sda.covidvavapp.config.VaccinationTypeConfig;

@SpringBootApplication
@EnableConfigurationProperties(value = {VaccinationTypeConfig.class})
public class CovidVavAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(CovidVavAppApplication.class, args);
    }

}
