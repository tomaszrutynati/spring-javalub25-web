package pl.sda.covidvavapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacilityRepository extends JpaRepository<FacilityEntity, Long> {

    List<FacilityEntity> findAllByZipCodeOrderByName(String zipCode);
}
