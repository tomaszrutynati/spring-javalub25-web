package pl.sda.covidvavapp.repository;

import org.springframework.stereotype.Repository;
import pl.sda.covidvavapp.api.model.FacilitySearchParams;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class FacilityRepository {
    private Long FACILITY_ID = 0L;

    private List<FacilityEntity> facilities = new ArrayList<>();

    public void create(FacilityEntity facility) {
        facility.setId(++FACILITY_ID);
        facilities.add(facility);
    }

    public void update(FacilityEntity facility) {
        remove(facility.getId());
        facilities.add(facility);
    }

    public void remove(Long id) {
        facilities.removeIf(fac -> fac.getId().equals(id));
    }

    public List<FacilityEntity> getAll() {
        return facilities;
    }

    public Optional<FacilityEntity> getOne(Long id) {
        return facilities.stream().filter(fac -> fac.getId().equals(id)).findFirst();
    }

    public List<FacilityEntity> findByParams(FacilitySearchParams params) {
        return facilities.stream()
                .filter(fac -> isNullOrEmpty(params.getCity()) || fac.getCity().equalsIgnoreCase(params.getCity()))
                .filter(fac -> isNullOrEmpty(params.getStreet()) || fac.getCity().equalsIgnoreCase(params.getStreet()))
                .filter(fac -> isNullOrEmpty(params.getZipCode()) || fac.getCity().equalsIgnoreCase(params.getZipCode()))
                .collect(Collectors.toList());

    }

    private boolean isNullOrEmpty(String toTest) {
        return toTest != null && !toTest.isEmpty();
    }

}
