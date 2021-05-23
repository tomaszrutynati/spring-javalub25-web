package pl.sda.covidvavapp.api.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientSearchParams {
    private String firstName;
    private String lastName;
    private LocalDate birthDateFrom;
    private LocalDate birthDateTo;
}
