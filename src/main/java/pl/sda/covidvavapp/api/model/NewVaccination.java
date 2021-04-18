package pl.sda.covidvavapp.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.sda.covidvavapp.api.validator.VaccineTimeRange;
import pl.sda.covidvavapp.api.validator.VaccineType;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@VaccineTimeRange
public class NewVaccination {
    private Long patientId;
    private String address;
    @VaccineType
    private String vacType;
    private LocalDate date;
}
