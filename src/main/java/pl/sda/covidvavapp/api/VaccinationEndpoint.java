package pl.sda.covidvavapp.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.sda.covidvavapp.api.model.NewVaccination;
import pl.sda.covidvavapp.service.VaccinationService;

@RestController
@RequestMapping("/vaccination")
@AllArgsConstructor
public class VaccinationEndpoint {

    private VaccinationService vaccinationService;

    @PostMapping
    public void planVaccination(@RequestBody NewVaccination vaccination) {
        vaccinationService.planVaccination(vaccination);
    }

    @DeleteMapping
    public void removePlannedVaccination(@RequestParam Long patientId) {
        vaccinationService.removePlannedVaccination(patientId);
    }
}
