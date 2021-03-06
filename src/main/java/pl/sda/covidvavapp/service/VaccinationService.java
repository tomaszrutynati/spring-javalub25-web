package pl.sda.covidvavapp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.covidvavapp.api.model.NewVaccination;
import pl.sda.covidvavapp.api.model.Vaccination;
import pl.sda.covidvavapp.exception.PatientAlreadyVaccinatedException;
import pl.sda.covidvavapp.repository.PatientEntity;
import pl.sda.covidvavapp.repository.PatientRepository;
import pl.sda.covidvavapp.repository.VaccinationEntity;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VaccinationService {

    private PatientRepository patientRepository;

    @Transactional
    public Set<Vaccination> planVaccination(NewVaccination vaccination) {
        if (vaccination == null) {
            return new HashSet<>();
        }

        return patientRepository.findById(vaccination.getPatientId()).map(patient -> {
                    if (!patient.getVaccinations().isEmpty()) {
                        throw new PatientAlreadyVaccinatedException("Patient has planned vaccinations");
                    }
                    patient.planVaccinations(vaccination.getAddress(),
                            vaccination.getVacType(), vaccination.getDate());

                    return patient.getVaccinations().stream()
                            .map(ent -> new Vaccination(ent.getDate(), ent.getAddress(), ent.getVacType()))
                            .collect(Collectors.toSet());
                }
        ).orElseGet(HashSet::new);
    }

    public void removePlannedVaccination(Long patientId) {
        Optional<PatientEntity> maybePatient = patientRepository.findById(patientId);

        if (!maybePatient.isPresent()) {
            return;
        }
        PatientEntity patient = maybePatient.get();
        boolean hasAnyDoneVac = patient.getVaccinations().stream().anyMatch(VaccinationEntity::isDone);

        if (hasAnyDoneVac) {
            throw new PatientAlreadyVaccinatedException("Patient has done vac already");
        }

        patient.removeVaccinations();
    }
}
