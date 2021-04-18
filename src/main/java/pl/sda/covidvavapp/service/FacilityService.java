package pl.sda.covidvavapp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.covidvavapp.api.model.Facility;
import pl.sda.covidvavapp.api.model.FacilitySearchParams;
import pl.sda.covidvavapp.repository.FacilityEntity;
import pl.sda.covidvavapp.repository.FacilityRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FacilityService {

    private FacilityRepository facilityRepository;

    public List<Facility> findByParams(FacilitySearchParams searchParams) {
        return facilityRepository.findByParams(searchParams)
                .stream()
                .map(this::mapToModel)
                .collect(Collectors.toList());
    }

    public void create(Facility facility) {
        FacilityEntity entity = mapToEntity(facility);

        facilityRepository.create(entity);
    }

    public void update(Facility facility) {
        facilityRepository.getOne(facility.getId()).ifPresent(fac -> {
            FacilityEntity entity = mapToEntity(facility);
            entity.setId(facility.getId());

            facilityRepository.update(entity);
        });
    }

    public void remove(Long id) {
        facilityRepository.remove(id);
    }

    public List<Facility> getAll() {
        return facilityRepository.getAll()
                .stream()
                .map(this::mapToModel)
                .collect(Collectors.toList());
    }

    public Facility getOne(Long id) {
        return facilityRepository.getOne(id)
                .map(this::mapToModel)
                .orElseThrow(() -> new IllegalStateException("Facility doesn't exist"));
    }

    private Facility mapToModel(FacilityEntity ent) {
        return Facility.builder()
                .id(ent.getId())
                .city(ent.getCity())
                .zipCode(ent.getZipCode())
                .phoneNumber(ent.getPhoneNumber())
                .houseNumber(ent.getHouseNumber())
                .street(ent.getStreet())
                .name(ent.getName())
                .build();
    }

    private FacilityEntity mapToEntity(Facility facility) {
        return FacilityEntity.builder()
                .city(facility.getCity())
                .zipCode(facility.getZipCode())
                .phoneNumber(facility.getPhoneNumber())
                .houseNumber(facility.getHouseNumber())
                .street(facility.getStreet())
                .name(facility.getName())
                .build();
    }
}
