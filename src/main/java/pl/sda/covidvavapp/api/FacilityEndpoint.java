package pl.sda.covidvavapp.api;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.sda.covidvavapp.api.model.Facility;
import pl.sda.covidvavapp.api.model.FacilitySearchParams;
import pl.sda.covidvavapp.service.FacilityService;

import java.util.List;

@RestController
@RequestMapping("/facility")
@AllArgsConstructor
public class FacilityEndpoint {

    private FacilityService facilityService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Facility facility) {
        facilityService.create(facility);
    }

    @PutMapping
    public void update(@RequestBody Facility facility) {
        facilityService.update(facility);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        facilityService.remove(id);
    }

    @GetMapping
    public List<Facility> getAll() {
        return facilityService.getAll();
    }

    @PostMapping("/search")
    public List<Facility> searchByParams(@RequestBody FacilitySearchParams searchParams) {
        return facilityService.findByParams(searchParams);
    }
}
