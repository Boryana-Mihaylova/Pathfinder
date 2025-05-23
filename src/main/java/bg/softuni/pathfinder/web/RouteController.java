package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.service.RouteService;
import bg.softuni.pathfinder.service.dto.RouteShortInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class RouteController {

    private final RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/routes")
    public String routes(Model model){

        //RouteShortInfoDto randomRoute = routeService.getRandomRoute();

        List<RouteShortInfoDto> routes = routeService.getAll();

        model.addAttribute("allRoutes", routes);

        return "routes";
    }
}
