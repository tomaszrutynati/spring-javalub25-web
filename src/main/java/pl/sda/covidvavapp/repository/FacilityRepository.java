package pl.sda.covidvavapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacilityRepository extends
        JpaRepository<FacilityEntity, Long>, CustomFacilityRepository {

    List<FacilityEntity> findAllByZipCodeOrderByName(String zipCode);

    //2
    void deleteAllByZipCode(String zipCode);

    //4
    List<FacilityEntity> findAllByZipCodeStartingWith(String zipCodePrefix);

    //7
    List<FacilityEntity> findAllByCityIgnoreCase(String city);
}
