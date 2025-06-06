package bg.softuni.pathfinder.service;


import bg.softuni.pathfinder.model.view.RouteViewModel;
import bg.softuni.pathfinder.repository.RouteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;

    private final ModelMapper modelMapper;

    public RouteServiceImpl(RouteRepository routeRepository,
                            ModelMapper modelMapper) {
        this.routeRepository = routeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RouteViewModel> findAllRoutesView() {
        return routeRepository
                .findAll()
                .stream()
                .map(route ->
                {
                    RouteViewModel routeViewModel = modelMapper.map(route, RouteViewModel.class);
                    if (route.getPictures().isEmpty()) {
                        routeViewModel.setPictureUrl("/images/pic4.jpg");
                    } else {
                        routeViewModel
                                .setPictureUrl(route
                                        .getPictures()
                                        .stream()
                                        .findFirst()
                                        .get()
                                        .getUrl());
                    }

                    return routeViewModel;
                })
                .collect(Collectors.toList());

    }
}
