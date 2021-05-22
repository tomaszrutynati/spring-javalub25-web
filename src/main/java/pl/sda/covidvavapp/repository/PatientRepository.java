package pl.sda.covidvavapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PatientRepository extends JpaRepository<PatientEntity, Long> {
    List<PatientEntity> findAllByBirthDateBefore(LocalDate olderThan);
    List<PatientEntity> findAllByBirthDateBetween(LocalDate youngerThan, LocalDate olderThan);
}
