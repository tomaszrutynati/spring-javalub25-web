package pl.sda.covidvavapp.repository;

import lombok.*;
import pl.sda.covidvavapp.api.model.Vaccination;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class PatientEntity {
    @Setter
    private Long id;
    private String firstName;
    private String lastName;
    private String pesel;
    private Set<Vaccination> vaccinations = new HashSet<>();
}
