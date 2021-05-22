package pl.sda.covidvavapp.web;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import pl.sda.covidvavapp.api.model.NewPatient;
import pl.sda.covidvavapp.service.PatientService;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@RequestMapping("/patient")
@AllArgsConstructor
public class PatientController {

    private PatientService patientService;

    @GetMapping
    public ModelAndView displayPatientsPage() {
        ModelAndView mav = new ModelAndView("patients");
        mav.addObject("patients", patientService.getAllPatients());
        return mav;
    }

    @GetMapping("/byAge")
    public ModelAndView displayPatientsPageByAge(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate olderThan) {
        ModelAndView mav = new ModelAndView("patients");
        mav.addObject("patients", patientService.getOlderThan(olderThan));
        return mav;
    }

    @GetMapping("/add")
    public ModelAndView displayAddPatientPage() {
        ModelAndView mav = new ModelAndView("addPatient");
        mav.addObject("newPatient", new NewPatient());
        return mav;
    }

    @PostMapping
    public String handleAddPatient(@Valid @ModelAttribute NewPatient newPatient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addPatient";
        }

        patientService.registerPatient(newPatient);

        return "redirect:/patient";
    }
}
