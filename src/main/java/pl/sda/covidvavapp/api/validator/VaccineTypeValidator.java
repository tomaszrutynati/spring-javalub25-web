package pl.sda.covidvavapp.api.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class VaccineTypeValidator implements ConstraintValidator<VaccineType, String> {

    private static final List<String> VAC_TYPES =
            Arrays.asList("Moderna", "Pfizer", "AstraZeneca");

    @Override
    public void initialize(VaccineType constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return VAC_TYPES.contains(value);
    }
}
