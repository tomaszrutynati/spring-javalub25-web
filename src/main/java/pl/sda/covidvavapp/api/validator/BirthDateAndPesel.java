package pl.sda.covidvavapp.api.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = BirthDateAndPeselValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface BirthDateAndPesel {
    String message() default "Different birth date in pesel";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
