package pl.sda.covidvavapp.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.util.Assert;
import pl.sda.covidvavapp.api.model.NewVaccination;
import pl.sda.covidvavapp.api.model.Vaccination;
import pl.sda.covidvavapp.exception.PatientAlreadyVaccinatedException;
import pl.sda.covidvavapp.repository.PatientEntity;
import pl.sda.covidvavapp.repository.PatientRepository;
import pl.sda.covidvavapp.repository.VaccinationEntity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class VaccinationServiceTest {

    private PatientRepository patientRepository = Mockito.mock(PatientRepository.class);
    private VaccinationService vaccinationService = new VaccinationService(patientRepository);

    @Test
    public void shouldPlanVaccinationWhenPatientIsPresent() {
        //given
        NewVaccination newVaccination = new NewVaccination();
        String vacType = "Pfizer";
        newVaccination.setVacType(vacType);
        String address = "Lublin, Jana Pawla II";
        newVaccination.setAddress(address);
        LocalDate vaccinationDate = LocalDate.now().plusMonths(1);
        newVaccination.setDate(vaccinationDate);
        newVaccination.setPatientId(1L);

        PatientEntity patient = PatientEntity.builder()
                .id(1L)
                .firstName("Adam")
                .lastName("Małysz")
                .pesel("91030302121")
                .birthDate(LocalDate.parse("1991-03-03"))
                .vaccinations(new HashSet<>())
                .build();

        Mockito.when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        //when
        Set<Vaccination> plannedVaccinations = vaccinationService.planVaccination(newVaccination);
        //then
        Assertions.assertEquals(2, plannedVaccinations.size());

        Assertions.assertTrue(plannedVaccinations.stream().anyMatch(vac -> vac.getDate().equals(vaccinationDate)));
        Assertions.assertTrue(plannedVaccinations.stream().anyMatch(vac -> vac.getDate().equals(vaccinationDate.plusWeeks(2))));
        Assertions.assertTrue(plannedVaccinations.stream().allMatch(vac -> vac.getVacType().equals(vacType)));
        Assertions.assertTrue(plannedVaccinations.stream().anyMatch(vac -> vac.getAddress().equals(address)));
    }

    @Test
    public void shouldNotPlanVaccinationWhenPatientIsNotPresent() {
        //given
        NewVaccination newVaccination = new NewVaccination();
        String vacType = "Pfizer";
        newVaccination.setVacType(vacType);
        String address = "Lublin, Jana Pawla II";
        newVaccination.setAddress(address);
        LocalDate vaccinationDate = LocalDate.now().plusMonths(1);
        newVaccination.setDate(vaccinationDate);
        newVaccination.setPatientId(1L);

        Mockito.when(patientRepository.findById(1L)).thenReturn(Optional.empty());
        //when
        Set<Vaccination> plannedVaccinations = vaccinationService.planVaccination(newVaccination);
        //then
        Assertions.assertEquals(0, plannedVaccinations.size());
    }

    @Test
    public void shouldNotPlanVaccinationWhenPatientIdIsNull() {
        //given
        NewVaccination newVaccination = new NewVaccination();
        String vacType = "Pfizer";
        newVaccination.setVacType(vacType);
        String address = "Lublin, Jana Pawla II";
        newVaccination.setAddress(address);
        LocalDate vaccinationDate = LocalDate.now().plusMonths(1);
        newVaccination.setDate(vaccinationDate);
        newVaccination.setPatientId(null);
        //when
        Set<Vaccination> plannedVaccinations = vaccinationService.planVaccination(newVaccination);
        //then
        Assertions.assertEquals(0, plannedVaccinations.size());
    }

    @Test
    public void shouldNotPlanVaccinationWhenVaccinationIsNull() {
        //when
        Set<Vaccination> plannedVaccinations = vaccinationService.planVaccination(null);
        //then
        Assertions.assertEquals(0, plannedVaccinations.size());
    }

    @Test
    public void shouldNotPlanVaccinationWhenPatientHasAlreadyVaccinations() {
        //given
        NewVaccination newVaccination = new NewVaccination();
        String vacType = "Pfizer";
        newVaccination.setVacType(vacType);
        String address = "Lublin, Jana Pawla II";
        newVaccination.setAddress(address);
        LocalDate vaccinationDate = LocalDate.now().plusMonths(1);
        newVaccination.setDate(vaccinationDate);
        newVaccination.setPatientId(1L);

        Set<VaccinationEntity> vaccinations = new HashSet<>();
        vaccinations.add(VaccinationEntity.builder()
                .vacType("Pfizer")
                .done(false)
                .date(LocalDate.now())
                .address("Lublin, Al. kraśnickie")
                .id(1L)
                .build());

        PatientEntity patient = PatientEntity.builder()
                .id(1L)
                .firstName("Adam")
                .lastName("Małysz")
                .pesel("91030302121")
                .birthDate(LocalDate.parse("1991-03-03"))
                .vaccinations(vaccinations)
                .build();

        Mockito.when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        //when
        Assertions.assertThrows(PatientAlreadyVaccinatedException.class,
                () -> vaccinationService.planVaccination(newVaccination));
    }
}