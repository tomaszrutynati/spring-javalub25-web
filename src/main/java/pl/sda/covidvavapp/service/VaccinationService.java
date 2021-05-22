package pl.sda.covidvavapp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.covidvavapp.api.model.NewVaccination;
import pl.sda.covidvavapp.exception.PatientAlreadyVaccinatedException;
import pl.sda.covidvavapp.repository.PatientEntity;
import pl.sda.covidvavapp.repository.PatientRepository;
import pl.sda.covidvavapp.repository.VaccinationEntity;

import java.util.Optional;

@Service
@AllArgsConstructor
public class VaccinationService {

    private PatientRepository patientRepository;

    public void planVaccination(NewVaccination vaccination) {
        patientRepository.findById(vaccination.getPatientId()).ifPresent(patient -> {
                    if (!patient.getVaccinations().isEmpty()) {
                        throw new PatientAlreadyVaccinatedException("Patient has planned vaccinations");
                    }
                    patient.planVaccinations(vaccination.getAddress(),
                            vaccination.getVacType(), vaccination.getDate());
                }
        );
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
