package pl.sda.covidvavapp.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.sda.covidvavapp.api.model.PatientSearchParams;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class CustomPatientRepositoryITTest {

    @Autowired
    private PatientRepository patientRepository;

    @BeforeEach
    public void preparePatientsData() {
        patientRepository.deleteAll();
        patientRepository.save(PatientEntity.builder().firstName("Adam").lastName("Malysz")
                .birthDate(LocalDate.parse("2000-10-10")).build());
        patientRepository.save(PatientEntity.builder().firstName("Adam").lastName("Lewandowski")
                .birthDate(LocalDate.parse("2005-07-07")).build());
        patientRepository.save(PatientEntity.builder().firstName("Anna").lastName("Lewandowska")
                .birthDate(LocalDate.parse("1990-01-05")).build());
        patientRepository.save(PatientEntity.builder().firstName("Robert").lastName("Lewandowski")
                .birthDate(LocalDate.parse("1990-01-06")).build());
        patientRepository.save(PatientEntity.builder().firstName("Robert").lastName("Kubica")
                .birthDate(LocalDate.parse("2000-01-10")).build());
        patientRepository.save(PatientEntity.builder().firstName("Joanna").lastName("Kowalska")
                .birthDate(LocalDate.parse("1993-05-05")).build());
    }

    @Test
    public void shouldFindByFirstName() {
        //given
        PatientSearchParams searchParams = new PatientSearchParams();
        searchParams.setFirstName("Robert");
        //when
        List<PatientEntity> patients = patientRepository.findWithSearchParams(searchParams);
        //then
        Assertions.assertEquals(2, patients.size());
        Assertions.assertTrue(patients.stream().allMatch(pat -> pat.getFirstName().equals("Robert")));
    }

}
