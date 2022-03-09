package ITSOL.Bus.Management.Full.Stack.Service.Impl;

import ITSOL.Bus.Management.Full.Stack.DAO.Entity.Route;
import ITSOL.Bus.Management.Full.Stack.DAO.Repository.RouteRepository;
import ITSOL.Bus.Management.Full.Stack.DTO.Request.RouteRequest;
import ITSOL.Bus.Management.Full.Stack.DTO.Response.RouteResponse;
import ITSOL.Bus.Management.Full.Stack.Exception.ResourceNotFoundException;
import ITSOL.Bus.Management.Full.Stack.Service.AbstractService;
import ITSOL.Bus.Management.Full.Stack.Service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl extends AbstractService implements RouteService {

    @Autowired
    RouteRepository routeRepository;

    @Override
    public Optional<List<RouteResponse>> getAllRoute() {
        List<Route> routes = routeRepository.getAllRoute().orElseThrow(() -> {
            throw new ResourceNotFoundException("Resource not found");
        });
        return Optional.of(routes.stream().map(route -> new RouteResponse(route.getRoute_ID(), route.getDistance(),
                route.getStations())).collect(Collectors.toList()));
    }

    @Override
    public Optional<RouteResponse> getRouteById(int id) {
        Route route = routeRepository.getRouteById(id).orElseThrow(()->{
            throw new ResourceNotFoundException("Resource not found");
        });
        return Optional.of(new RouteResponse(route.getRoute_ID(), route.getDistance(), route.getStations()));
    }

    @Override
    public Optional<RouteResponse> addRoute(RouteRequest routeRequest) {
        List<RouteResponse> routeResponses = getAllRoute().orElseThrow(()->{
            throw new ResourceNotFoundException("Resource can't found");
        });
        int curId = routeResponses.size() + 100;
        Route route = new Route(curId, routeRequest.getDistance(), routeRequest.getStations(),0);
        routeRepository.addRoute(route);
        return Optional.of(objectMapper.convertValue(route, RouteResponse.class));
    }

    @Override
    public Optional<RouteResponse> updateRoute(RouteRequest routeRequest, int id) {
        Route route = routeRepository.getRouteById(id).orElseThrow(()->{
            throw new ResourceNotFoundException("Can't found resource");
        });
        routeRepository.updateRoute(objectMapper.convertValue(routeRequest, Route.class),id);
        return getRouteById(id);
    }

    @Override
    public Optional<RouteResponse> deleteRoute(int id) {
        Route route = routeRepository.getRouteById(id).orElseThrow(()->{
            throw new ResourceNotFoundException("Delete Successfully");
        });
        routeRepository.deleteRoute(id);
        return Optional.of(objectMapper.convertValue(route, RouteResponse.class));
    }
}