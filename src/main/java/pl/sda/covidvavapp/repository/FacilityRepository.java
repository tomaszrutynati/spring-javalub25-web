package pl.sda.covidvavapp.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

}
