package pl.sda.covidvavapp.api.validator;

import org.springframework.beans.factory.annotation.Autowired;
import pl.sda.covidvavapp.api.model.NewVaccination;
import pl.sda.covidvavapp.config.VaccinationTypeConfig;
import pl.sda.covidvavapp.config.VaccinationTypeConfig.DateRange;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class VaccineTimeRangeValidator implements ConstraintValidator<VaccineTimeRange, NewVaccination> {

    @Autowired
    private VaccinationTypeConfig config;

    @Override
    public void initialize(VaccineTimeRange constraintAnnotation) {
    }

    @Override
    public boolean isValid(NewVaccination vaccination, ConstraintValidatorContext constraintValidatorContext) {
        DateRange dateRange = config.getTypes().get(vaccination.getVacType().toLowerCase());
        LocalDate vaccinationDate = vaccination.getDate();
        return dateRange != null && (vaccinationDate.isAfter(dateRange.getStart()) && vaccinationDate.isBefore(dateRange.getEnd()));
    }
}
