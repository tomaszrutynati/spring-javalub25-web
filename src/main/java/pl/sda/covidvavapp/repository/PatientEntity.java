package pl.sda.covidvavapp.repository;

import lombok.*;

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
    private Set<VaccinationEntity> vaccinations = new HashSet<>();

    public PatientEntity updatePatient(String newFirstName, String newLastName) {
        this.firstName = newFirstName;
        this.lastName = newLastName;
        return this;
    }
}
