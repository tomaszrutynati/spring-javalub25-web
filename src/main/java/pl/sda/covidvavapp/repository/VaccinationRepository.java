package pl.sda.covidvavapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VaccinationRepository extends JpaRepository<VaccinationEntity, Long> {

    List<VaccinationEntity> findAllByPatient_pesel(String pesel);

}
