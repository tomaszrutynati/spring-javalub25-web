package pl.sda.covidvavapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@Controller
public class MainPageController {

  /*  @GetMapping("/")
    public String displayMainPage(final ModelMap modelMap) {
        modelMap.addAttribute("currentDate", LocalDate.now());
        return "main";
    }*/

    @GetMapping("/")
    public ModelAndView displayMainPage() {
        ModelAndView mav = new ModelAndView("main");
        mav.addObject("currentDate", LocalDate.now());
        return mav;
    }

}
