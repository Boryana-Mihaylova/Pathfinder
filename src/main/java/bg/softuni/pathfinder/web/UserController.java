package bg.softuni.pathfinder.web;


import bg.softuni.pathfinder.config.UserSession;
import bg.softuni.pathfinder.service.UserService;
import bg.softuni.pathfinder.web.dto.UserLoginDTO;
import bg.softuni.pathfinder.web.dto.UserRegisterDTO;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserSession userSession;

    private final ModelMapper modelMapper;


    public UserController(UserService userService, UserSession userSession, ModelMapper modelMapper) {
        this.userService = userService;
        this.userSession = userSession;
        this.modelMapper = modelMapper;
    }
    @ModelAttribute("registerData")
    public UserRegisterDTO registerDTO(){
        return new UserRegisterDTO();
    }

    @ModelAttribute("loginData")
    public UserLoginDTO loginData (){
        return new UserLoginDTO();
    }

    @GetMapping("/register")
    public String viewRegister(Model model){

        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@Valid UserRegisterDTO data, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors() || !data.getPassword().equals(data.getConfirmPassword())){
            redirectAttributes.addFlashAttribute("registerData", data);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.registerData", bindingResult);

            return "redirect:register";
        }

        boolean success = userService.register(data);

        if (!success) {
            return "redirect:register";
        }

        return "redirect:login";
    }

    @GetMapping("/login")
    public String login(Model model){

        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@Valid UserLoginDTO data, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (userSession.isLoggedIn()){
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginData", data);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.loginData", bindingResult);

            return "redirect:login";
        }

        boolean success = userService.login(data);

        if (!success){
            redirectAttributes.addFlashAttribute("loginError", true);
            return "redirect:login";
        }

        return "redirect:/";

    }

    @GetMapping("/profile/{id}")
    public String profile(@PathVariable long id) {


        return "profile";
    }

//    @GetMapping("/logout")
//    public String logout(){
//
//        if (!userSession.isLoggedIn()){
//            return "redirect:/";
//        }
//
//        userSession.logout();
//
//        return "redirect:/";
//    }
}
