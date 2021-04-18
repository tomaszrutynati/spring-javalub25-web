package pl.sda.covidvavapp.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.covidvavapp.api.model.Facility;
import pl.sda.covidvavapp.api.model.NewPatient;
import pl.sda.covidvavapp.service.FacilityService;
import pl.sda.covidvavapp.service.PatientService;

@Controller
@RequestMapping("/facility")
@AllArgsConstructor
public class FacilityController {

    private FacilityService facilityService;

    @GetMapping
    public ModelAndView displayAddFacilityPage() {
        ModelAndView mav = new ModelAndView("addFacility");
        mav.addObject("newFacility", new Facility());
        return mav;
    }

    @PostMapping
    public String handleAddFacility(@ModelAttribute Facility newFacility) {
        facilityService.create(newFacility);

        return "main";
    }
}
