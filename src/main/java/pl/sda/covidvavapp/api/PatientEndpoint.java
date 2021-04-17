package pl.sda.covidvavapp.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.sda.covidvavapp.api.model.NewPatient;
import pl.sda.covidvavapp.api.model.Patient;
import pl.sda.covidvavapp.api.model.UpdatePatient;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientEndpoint {

    @GetMapping
    public List<Patient> getAll() {
        return null;
    }

    @GetMapping("/{id}")
    public Patient getOne(@PathVariable Long id) {
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerPatient(@RequestBody NewPatient patient) {

    }

    @PutMapping
    public void updatePatient(@RequestBody UpdatePatient patient) {

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePatient(@PathVariable Long id) {

    }
}
