package pl.sda.covidvavapp.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.covidvavapp.api.model.NewVaccination;
import pl.sda.covidvavapp.config.VaccinationTypeConfig;
import pl.sda.covidvavapp.service.FacilityService;
import pl.sda.covidvavapp.service.PatientService;
import pl.sda.covidvavapp.service.VaccinationService;
import pl.sda.covidvavapp.web.model.Dictionary;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/vaccination")
@AllArgsConstructor
public class VaccinationController {

    private VaccinationService vaccinationService;
    private FacilityService facilityService;
    private PatientService patientService;
    private VaccinationTypeConfig vaccinationTypeConfig;

    @GetMapping
    public ModelAndView displayAddVaccinationPage() {
        ModelAndView mav = new ModelAndView("addVaccination");
        mav.addObject("newVaccination", new NewVaccination());
        mav.addObject("patients", preparePatients());
        mav.addObject("types", prepareTypes());
        mav.addObject("facilities", prepareFacilities());
        return mav;
    }

    @PostMapping
    public String handleAddVaccination(@ModelAttribute NewVaccination newVaccination) {
        vaccinationService.planVaccination(newVaccination);
        return "main";
    }

    private List<Dictionary> prepareFacilities() {
        return facilityService.getAll().stream()
                .map(fac -> new Dictionary(fac.getId().toString(), fac.getName()))
                .collect(Collectors.toList());
    }

    private List<Dictionary> prepareTypes() {
        return vaccinationTypeConfig.getTypes().keySet()
                .stream()
                .map(type -> new Dictionary(type, type.toUpperCase()))
                .collect(Collectors.toList());
    }

    private List<Dictionary> preparePatients() {
        return patientService.getAllPatients().stream()
                .map(pat -> new Dictionary(pat.getId().toString(), pat.getFirstName() + " " + pat.getLastName()))
                .collect(Collectors.toList());
    }

}
