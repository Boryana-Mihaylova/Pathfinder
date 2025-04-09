package bg.softuni.pathfinder.web;


import bg.softuni.pathfinder.web.dto.UserRegisterDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class UserController {


    @ModelAttribute("registerData")
    public UserRegisterDTO registerDTO(){
        return new UserRegisterDTO();
    }

//    @ModelAttribute("loginData")
//    public UserLoginDTO loginData (){
//        return new UserLoginDTO();
//    }

    @GetMapping("/users/register")
    public String viewRegister(){

        return "register";
    }

    @PostMapping("/users/register")
    public String doRegister(@Valid UserRegisterDTO data, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("registerData", data);

            return "redirect:register";
        }


        return "redirect:/users/login";
    }

    @GetMapping("/users/login")
    public String login(){


        return "login";
    }
//
//    @PostMapping("/login")
//    public String doLogin(@Valid UserLoginDTO data, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
//
//        if (userSession.isLoggedIn()){
//            return "redirect:/home";
//        }
//
//        if (bindingResult.hasErrors()) {
//
//            redirectAttributes.addFlashAttribute("loginData", data);
//            redirectAttributes.addFlashAttribute(
//                    "org.springframework.validation.BindingResult.loginData", bindingResult);
//
//            return "redirect:/login";
//        }
//
//        boolean success = userService.login(data);
//        if (!success){
//            redirectAttributes.addFlashAttribute("loginError", true);
//
//            return "redirect:/login";
//        }
//
//        return "redirect:/home";
//
//    }

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
