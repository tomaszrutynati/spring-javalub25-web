package pl.sda.covidvavapp.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.sda.covidvavapp.api.model.NewVaccination;
import pl.sda.covidvavapp.service.VaccinationService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/vaccination")
@AllArgsConstructor
public class VaccinationEndpoint {

    private VaccinationService vaccinationService;

    @PostMapping
    public void planVaccination(@Valid @RequestBody NewVaccination vaccination) {
        vaccinationService.planVaccination(vaccination);
    }

    @DeleteMapping
    public void removePlannedVaccination(@RequestParam Long patientId) {
        vaccinationService.removePlannedVaccination(patientId);
    }
}
