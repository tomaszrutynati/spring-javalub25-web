package pl.sda.covidvavapp.api.validator;

import pl.sda.covidvavapp.api.model.NewPatient;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class BirthDateAndPeselValidator implements ConstraintValidator<BirthDateAndPesel, NewPatient> {
    @Override
    public boolean isValid(NewPatient newPatient, ConstraintValidatorContext constraintValidatorContext) {
        String pesel = newPatient.getPesel();

        int yearFromPesel = Integer.parseInt("19" + pesel.substring(0, 2));
        int monthFromPesel = Integer.parseInt(pesel.substring(2, 4));
        int dayFromPesel = Integer.parseInt(pesel.substring(4, 6));

        LocalDate birthDate = newPatient.getBirthDate();

        return birthDate.getYear() == yearFromPesel && birthDate.getMonthValue() == monthFromPesel
                && birthDate.getDayOfMonth() == dayFromPesel;
    }
}
