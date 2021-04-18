package pl.sda.covidvavapp.api.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class ZipCodeValidator implements ConstraintValidator<ZipCode, String> {

    private static final String ZIP_CODE_REGEX = "[0-9]{2}[\\-]{1}[0-9]{3}";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        Pattern zipCodePattern = Pattern.compile(ZIP_CODE_REGEX);

        return zipCodePattern.matcher(value).matches();
    }
}
