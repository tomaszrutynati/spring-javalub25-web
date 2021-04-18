package pl.sda.covidvavapp.api.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = VaccineTimeRangeValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface VaccineTimeRange {
    String message() default "This vaccine type is unavailable in this time range";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
