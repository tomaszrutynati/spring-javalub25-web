package pl.sda.covidvavapp.repository;

import pl.sda.covidvavapp.api.model.PatientSearchParams;

import java.util.List;

public interface CustomPatientRepository {

    List<PatientEntity> findWithSearchParams(PatientSearchParams searchParams);
}
