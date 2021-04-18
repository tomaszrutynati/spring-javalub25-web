package pl.sda.covidvavapp.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.covidvavapp.api.model.NewPatient;
import pl.sda.covidvavapp.service.PatientService;

@Controller
@RequestMapping("/patient")
@AllArgsConstructor
public class PatientController {

    private PatientService patientService;

    @GetMapping
    public ModelAndView displayAddPatientPage() {
        ModelAndView mav = new ModelAndView("addPatient");
        mav.addObject("newPatient", new NewPatient());
        return mav;
    }

    @PostMapping
    public String handleAddPatient(@ModelAttribute NewPatient newPatient) {
        patientService.registerPatient(newPatient);

        return "main";
    }
}
