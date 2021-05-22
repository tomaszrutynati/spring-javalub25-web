package pl.sda.covidvavapp.web;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
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
    public ModelAndView displayPatientsPageByAge(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate olderThan,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate youngerThan) {
        ModelAndView mav = new ModelAndView("patients");
        mav.addObject("patients", patientService.findByBirthDate(youngerThan, olderThan));
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
