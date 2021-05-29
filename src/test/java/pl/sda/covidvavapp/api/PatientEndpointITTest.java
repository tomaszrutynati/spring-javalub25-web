package pl.sda.covidvavapp.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pl.sda.covidvavapp.api.model.Errors;
import pl.sda.covidvavapp.api.model.NewPatient;
import pl.sda.covidvavapp.api.model.Patient;
import pl.sda.covidvavapp.repository.PatientEntity;
import pl.sda.covidvavapp.repository.PatientRepository;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PatientEndpointITTest {

    @LocalServerPort
    private Integer port;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private PatientRepository patientRepository;

    @BeforeEach
    public void beforeEach() {
        patientRepository.deleteAll();
    }

    @Test
    public void shouldCreateNewPatient() {
        //given
        NewPatient newPatient = new NewPatient();
        newPatient.setBirthDate(LocalDate.parse("1981-10-17"));
        newPatient.setPesel("81101723689");
        newPatient.setFirstName("Adam");
        newPatient.setLastName("Malysz");

        HttpEntity<NewPatient> entity = new HttpEntity<>(newPatient);

        //when
        ResponseEntity<Void> response = restTemplate.exchange(
                String.format("http://localhost:%d/api/patient", port),
                HttpMethod.POST,
                entity,
                Void.class);
        //then
        Assertions.assertEquals(201, response.getStatusCodeValue());
        List<PatientEntity> all = patientRepository.findAll();
        Assertions.assertEquals(1, all.size());
        PatientEntity patient = all.get(0);
        Assertions.assertEquals(LocalDate.parse("1981-10-17"), patient.getBirthDate());
        Assertions.assertEquals("81101723689", patient.getPesel());
        Assertions.assertEquals("Adam", patient.getFirstName());
        Assertions.assertEquals("Malysz", patient.getLastName());
    }

    @Test
    public void shouldNotCreateNewPatientBecauseOfWrongPesel() {
        //given
        NewPatient newPatient = new NewPatient();
        newPatient.setBirthDate(LocalDate.parse("1981-10-17"));
        newPatient.setPesel("8110172368");
        newPatient.setFirstName("Adam");
        newPatient.setLastName("Malysz");

        HttpEntity<NewPatient> entity = new HttpEntity<>(newPatient);

        //when
        ResponseEntity<Errors> response = restTemplate.exchange(
                String.format("http://localhost:%d/api/patient", port),
                HttpMethod.POST,
                entity,
                Errors.class);
        //then
        Assertions.assertEquals(400, response.getStatusCodeValue());
        List<PatientEntity> all = patientRepository.findAll();
        Assertions.assertEquals(0, all.size());

        Assertions.assertTrue(response.getBody().getErrors().contains("PESEL should have proper value"));
    }

    @Test
    public void shouldGetAllPatients() {
        //given
        patientRepository.save(PatientEntity.builder().firstName("Adam").lastName("Malysz")
                .birthDate(LocalDate.parse("2000-10-10")).build());
        patientRepository.save(PatientEntity.builder().firstName("Adam").lastName("Lewandowski")
                .birthDate(LocalDate.parse("2005-07-07")).build());
        patientRepository.save(PatientEntity.builder().firstName("Anna").lastName("Lewandowska")
                .birthDate(LocalDate.parse("1990-01-05")).build());
        RequestEntity<Void> requestEntity = RequestEntity.get(String.format("http://localhost:%d/api/patient", port)).build();
        //when
        ResponseEntity<List<Patient>> response = restTemplate.exchange(
                requestEntity,
                new ParameterizedTypeReference<List<Patient>>() {}
        );
        //then
        Assertions.assertEquals(200, response.getStatusCodeValue());
        List<Patient> patients = response.getBody();
        Assertions.assertEquals(3, patients.size());

        //kolejne assercje sprawdzajace zawartosc listy
    }
}
