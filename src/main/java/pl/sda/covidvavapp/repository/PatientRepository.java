package pl.sda.covidvavapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

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
}
