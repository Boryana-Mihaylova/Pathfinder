package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.model.binding.RouteAddBindingModel;
import bg.softuni.pathfinder.service.RouteService;
import bg.softuni.pathfinder.util.CurrentUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/routes")
public class RouteController {

    private final RouteService routeService;
    private final CurrentUser currentUser;

    @Autowired
    public RouteController(RouteService routeService, CurrentUser currentUser) {
        this.routeService = routeService;
        this.currentUser = currentUser;
    }

    @GetMapping("/all")
    public String allRoutes(Model model){

       model.addAttribute("routes", routeService.findAllRoutesView());

        return "routes";
    }

    @GetMapping("/add")
        public String add(Model model){

        if (currentUser.getId() == null){
            return "redirect:/users/login";

        }

        return "add-route";

    }

    @PostMapping("/add")
    public String addConfirm(@Valid RouteAddBindingModel routeAddBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){

        }

        return "redirect:all";

    }

    @ModelAttribute
    public RouteAddBindingModel routeAddBindingModel(){
        return new RouteAddBindingModel();
    }
}
