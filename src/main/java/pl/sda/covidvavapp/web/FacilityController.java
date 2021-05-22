package pl.sda.covidvavapp.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.covidvavapp.api.model.Facility;
import pl.sda.covidvavapp.api.model.NewPatient;
import pl.sda.covidvavapp.service.FacilityService;
import pl.sda.covidvavapp.service.PatientService;

import javax.validation.Valid;

@Controller
@RequestMapping("/facility")
@AllArgsConstructor
public class FacilityController {

    private FacilityService facilityService;


    @GetMapping
    public ModelAndView displayFacilitiesPage() {
        ModelAndView mav = new ModelAndView("facilities");
        mav.addObject("facilities", facilityService.getAll());
        return mav;
    }

    @GetMapping("/add")
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
    public String handleFacilityChange(@Valid @ModelAttribute Facility facility, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "changeFacility";
        }

        if (facility.getId() == null) {
            facilityService.create(facility);
        } else {
            facilityService.update(facility);
        }
        return "redirect:/facility";
    }
}
