package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/routes")
public class RouteController {

    private final RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/all")
    public String allRoutes(Model model){

       model.addAttribute("routes", routeService.findAllRoutesView());

        return "routes";
    }

    @GetMapping("/add")
        public String add(Model model){

        return "add-route";

    }
}
