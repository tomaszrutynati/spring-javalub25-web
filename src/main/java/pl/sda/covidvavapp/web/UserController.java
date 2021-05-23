package pl.sda.covidvavapp.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.covidvavapp.service.UserService;
import pl.sda.covidvavapp.web.model.NewUser;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/registration")
    public ModelAndView displayRegistrationPage() {
        ModelAndView mav = new ModelAndView("registration");
        mav.addObject("newUser", new NewUser());
        return mav;
    }

    @PostMapping("/registration")
    public String handleUserRegistration(@ModelAttribute NewUser newUser) {
        userService.registerUser(newUser);

        return "redirect:/";
    }
}
