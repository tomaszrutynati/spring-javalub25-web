package pl.sda.covidvavapp.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PatientRepository {
    private Long PATIENT_ID = 0L;
    private Long VAC_ID = 0L;

    private List<PatientEntity> patients = new ArrayList<>();

    public List<PatientEntity> getAll() {
        return patients;
    }

    public Optional<PatientEntity> getById(Long id) {
        return patients.stream().filter(pat -> pat.getId().equals(id)).findFirst();
    }

    public void delete(Long id) {
        patients.removeIf(pat -> pat.getId().equals(id));
    }

    public void create(PatientEntity patient) {
        patient.setId(++PATIENT_ID);

        patients.add(patient);
    }

    public void update(PatientEntity patient) {
        delete(patient.getId());

        patients.add(patient);
    }
}
