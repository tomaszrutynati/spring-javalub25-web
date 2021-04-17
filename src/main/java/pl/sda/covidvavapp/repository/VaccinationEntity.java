package pl.sda.covidvavapp.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class VaccinationEntity {
    private Long id;
    private String vacType;
    private String address;
    private LocalDate date;
    private boolean done;
}
