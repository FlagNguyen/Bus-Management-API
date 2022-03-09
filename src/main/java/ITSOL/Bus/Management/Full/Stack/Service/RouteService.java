package ITSOL.Bus.Management.Full.Stack.Service;

import ITSOL.Bus.Management.Full.Stack.DTO.Request.RouteRequest;
import ITSOL.Bus.Management.Full.Stack.DTO.Response.RouteResponse;

import java.util.List;
import java.util.Optional;

public interface RouteService {
    Optional<List<RouteResponse>> getAllRoute();
    Optional<RouteResponse> getRouteById(int id);
    Optional<RouteResponse> addRoute(RouteRequest RouteRequest);
    Optional<RouteResponse> updateRoute(RouteRequest RouteRequest, int id);
    Optional<RouteResponse> deleteRoute(int id);
}
