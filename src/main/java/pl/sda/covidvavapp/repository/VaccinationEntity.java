package pl.sda.covidvavapp.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "vaccinations")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class VaccinationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String vacType;
    private String address;
    private LocalDate date;
    private boolean done;
    @ManyToOne
    private PatientEntity patient;
}
