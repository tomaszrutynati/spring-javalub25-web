package pl.sda.covidvavapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<PatientEntity, Long> {
    List<PatientEntity> findAllByBirthDateBefore(LocalDate olderThan);
    List<PatientEntity> findAllByBirthDateBetween(LocalDate youngerThan, LocalDate olderThan);

    Optional<PatientEntity> findByPesel(String pesel);

    //1
    Long countAllByLastNameIgnoreCase(String lastName);

    //5
    List<PatientEntity> findAllByFirstNameIn(List<String> names);

    //Zapytanie, które zwróci wszystich pacjentów, którzy mają zaplanowane szczepienie
    // w podanym zakresie czasu i podanym typem szczepionki
    @Query("select distinct pat from PatientEntity pat " +
            "inner join pat.vaccinations vac " +
            "where vac.done = false and vac.date >= :startDate and vac.date <= :endDate and vac.vacType = :vacType")
    List<PatientEntity> findPatientsByVaccinationDate(@Param("startDate") LocalDate startDate,
                                                      @Param("endDate") LocalDate endDate,
                                                      @Param("vacType") String vacType);
    //Zapytania, który zwróci pesele wszystkich pacjentów, którzy mieli szczepienie pierwszego dnia szczepień
    @Query("select distinct pat.pesel from PatientEntity pat " +
            "inner join pat.vaccinations vac " +
            "where vac.date = (select min(vc.date) from VaccinationEntity vc)")
    List<String> findPatientsPeselsVaccinatedInFirstDay();
}
