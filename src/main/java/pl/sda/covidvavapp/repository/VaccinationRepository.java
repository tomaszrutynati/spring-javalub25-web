package pl.sda.covidvavapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.covidvavapp.api.validator.VaccineType;

import java.time.LocalDate;
import java.util.List;

public interface VaccinationRepository extends JpaRepository<VaccinationEntity, Long> {

    List<VaccinationEntity> findAllByPatient_pesel(String pesel);

    //3
    List<VaccinationEntity> findAllByVacType(String vacType);

    //6
    Long countAllByDateBeforeAndVacType(LocalDate date, String vacType);

}
