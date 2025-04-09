package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.Picture;
import bg.softuni.pathfinder.model.Route;
import bg.softuni.pathfinder.repository.RouteRepository;
import bg.softuni.pathfinder.service.dto.RouteShortInfoDto;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class RouteService {

    private final RouteRepository routeRepository;

    private Random random;

    private ModelMapper modelMapper;

    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
        this.modelMapper = new ModelMapper();
        this.random = new Random();
    }

    @Transactional
    public RouteShortInfoDto getRandomRoute(){

        long routeCount = routeRepository.count();

        random.nextLong(routeCount);
        long randomId = random.nextLong(routeCount) + 1;

        Optional<Route> route = routeRepository.findById(randomId);

        if (route.isEmpty()){

        }

        RouteShortInfoDto dto = modelMapper.map(route.get(), RouteShortInfoDto.class);
        Optional<Picture> first = route.get().getPictures().stream().findFirst();
        dto.setImageUrl(first.get().getUrl());

        return dto;

    }
}
