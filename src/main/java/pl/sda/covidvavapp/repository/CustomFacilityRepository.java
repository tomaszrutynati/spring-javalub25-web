package pl.sda.covidvavapp.repository;

import pl.sda.covidvavapp.api.model.FacilitySearchParams;

import java.util.List;

public interface CustomFacilityRepository {
    List<FacilityEntity> findWithSearchParams(FacilitySearchParams searchParams);
}
