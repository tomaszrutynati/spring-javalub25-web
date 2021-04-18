package pl.sda.covidvavapp.api.validator;

import pl.sda.covidvavapp.api.model.NewVaccination;
import pl.sda.covidvavapp.config.VaccinationTypeConfig;
import pl.sda.covidvavapp.config.VaccinationTypeConfig.DateRange;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.util.HashMap;

public class VaccineTimeRangeValidator implements ConstraintValidator<VaccineTimeRange, NewVaccination> {

    private VaccinationTypeConfig config;

    @Override
    public void initialize(VaccineTimeRange constraintAnnotation) {
        HashMap<String, DateRange> types = new HashMap<>();
        types.put("pfizer", new DateRange(LocalDate.parse("2021-01-01"), LocalDate.parse("2021-07-01")));
        types.put("moderna", new DateRange(LocalDate.parse("2021-02-01"), LocalDate.parse("2021-12-31")));
        types.put("astrazeneca", new DateRange(LocalDate.parse("2021-03-01"), LocalDate.parse("2021-04-20")));
        config = new VaccinationTypeConfig(types);
    }

    @Override
    public boolean isValid(NewVaccination vaccination, ConstraintValidatorContext constraintValidatorContext) {
        DateRange dateRange = config.getTypes().get(vaccination.getVacType().toLowerCase());
        LocalDate vaccinationDate = vaccination.getDate();
        return dateRange != null && (vaccinationDate.isAfter(dateRange.getStart()) && vaccinationDate.isBefore(dateRange.getEnd()));
    }
}
