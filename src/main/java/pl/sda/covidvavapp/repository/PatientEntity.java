package pl.sda.covidvavapp.repository;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "patients")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class PatientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String pesel;
    private LocalDate birthDate;
    @OneToMany(mappedBy = "patient")
    private Set<VaccinationEntity> vaccinations = new HashSet<>();

    public PatientEntity updatePatient(String newFirstName, String newLastName) {
        this.firstName = newFirstName;
        this.lastName = newLastName;
        return this;
    }

    public void removeVaccinations() {
        this.vaccinations = new HashSet<>();
    }

    public void planVaccinations(String address, String vacType, LocalDate localDate) {
        vaccinations.add(VaccinationEntity.builder()
                .address(address)
                .date(localDate)
                .done(false)
                .vacType(vacType)
                .build());
        vaccinations.add(VaccinationEntity.builder()
                .address(address)
                .date(localDate.plusWeeks(2))
                .done(false)
                .vacType(vacType)
                .build());
    }
}
