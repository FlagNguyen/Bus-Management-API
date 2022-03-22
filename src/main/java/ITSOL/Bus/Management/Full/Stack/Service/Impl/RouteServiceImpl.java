package ITSOL.Bus.Management.Full.Stack.Service.Impl;

import ITSOL.Bus.Management.Full.Stack.DAO.Entity.Route;
import ITSOL.Bus.Management.Full.Stack.DAO.Repository.RouteRepository;
import ITSOL.Bus.Management.Full.Stack.DTO.Request.RouteRequest;
import ITSOL.Bus.Management.Full.Stack.DTO.Response.RouteResponse;
import ITSOL.Bus.Management.Full.Stack.Exception.InvalidFloatFormat;
import ITSOL.Bus.Management.Full.Stack.Exception.InvalidIntegerFormat;
import ITSOL.Bus.Management.Full.Stack.Exception.InvalidRequestException;
import ITSOL.Bus.Management.Full.Stack.Exception.ResourceNotFoundException;
import ITSOL.Bus.Management.Full.Stack.Service.AbstractService;
import ITSOL.Bus.Management.Full.Stack.Service.RouteService;
import ITSOL.Bus.Management.Full.Stack.Utility.ValidateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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

    private Optional<List<RouteResponse>> getTotalRoute() {
        List<Route> routes = routeRepository.getTotalRoute().orElseThrow(() -> {
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
        validateRouteRequestAndReturnMessage(routeRequest);

        Optional<List<RouteResponse>> routeResponses = getTotalRoute();
        int curId = routeResponses.get().size() + 101;

        Route route = new Route(curId, Float.parseFloat(routeRequest.getDistance()), Integer.parseInt(routeRequest.getStations()),0);
        routeRepository.addRoute(route);
        return Optional.of(objectMapper.convertValue(routeRequest, RouteResponse.class));
    }

    @Override
    public Optional<RouteResponse> updateRoute(RouteRequest routeRequest, int id) {
        validateRouteRequestAndReturnMessage(routeRequest);
        Route route = routeRepository.getRouteById(id).orElseThrow(()->{
            throw new ResourceNotFoundException("Can't found this id");
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

    private void validateRouteRequestAndReturnMessage(RouteRequest routeRequest){
        String mess = objectValidator.validateRequestAndReturnMessage(routeRequest);
        if(!ObjectUtils.isEmpty(mess)){
            throw new InvalidRequestException(mess);
        }
        if(ObjectUtils.isEmpty(ValidateRequest.validInteger(routeRequest.getStations()))){
            throw new InvalidIntegerFormat(mess);
        }
        if(ObjectUtils.isEmpty(ValidateRequest.validFloat(routeRequest.getDistance()))){
            throw new InvalidFloatFormat(mess);
        }
    }
}
