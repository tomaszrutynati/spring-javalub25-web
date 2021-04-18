package pl.sda.covidvavapp.api.validator;

import org.springframework.beans.factory.annotation.Autowired;
import pl.sda.covidvavapp.config.VaccinationTypeConfig;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VaccineTypeValidator implements ConstraintValidator<VaccineType, String> {

    @Autowired
    private VaccinationTypeConfig config;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return config.getTypes().keySet().contains(value.toLowerCase());
    }
}
