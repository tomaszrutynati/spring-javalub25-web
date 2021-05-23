package pl.sda.covidvavapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface VaccinationRepository extends JpaRepository<VaccinationEntity, Long> {

    List<VaccinationEntity> findAllByPatient_pesel(String pesel);

    //3
    List<VaccinationEntity> findAllByVacType(String vacType);

    //6
    Long countAllByDateBeforeAndVacType(LocalDate date, String vacType);

    @Query("select vac from VaccinationEntity vac inner join vac.patient pat where pat.pesel = :pesel")
    List<VaccinationEntity> findByPatientsPesel(@Param("pesel") String pesel);

    @Query("select vac.date from VaccinationEntity vac " +
            "inner join vac.patient pat " +
            "where pat.birthDate = (select min(p.birthDate) from PatientEntity p)")
    List<LocalDate> findVacsDatesForOldestPatient();

}
