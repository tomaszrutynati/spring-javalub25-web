package pl.sda.covidvavapp.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
        ModelAndView mav = new ModelAndView("changeFacility");
        mav.addObject("facility", new Facility());
        return mav;
    }

    @GetMapping("/edit/{facilityId}")
    public ModelAndView displayEditFacilityPage(@PathVariable Long facilityId) {
        ModelAndView mav = new ModelAndView("changeFacility");
        mav.addObject("facility", facilityService.getOne(facilityId));
        return mav;
    }

    @PostMapping
    public String handleFacilityChange(@ModelAttribute Facility facility) {
        if (facility.getId() == null) {
            facilityService.create(facility);
        } else {
            facilityService.update(facility);
        }
        return "main";
    }
}
