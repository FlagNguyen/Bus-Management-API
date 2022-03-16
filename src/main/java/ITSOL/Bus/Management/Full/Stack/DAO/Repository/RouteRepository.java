package ITSOL.Bus.Management.Full.Stack.DAO.Repository;

import ITSOL.Bus.Management.Full.Stack.DAO.Entity.Route;


import java.util.List;
import java.util.Optional;

public interface RouteRepository{
    Optional<List<Route>> getAllRoute();
    Optional<List<Route>> getTotalRoute();
    Optional<Route> getRouteById(int id);
    Optional<Route> addRoute(Route route);
    Optional<Route> updateRoute(Route route, int id);
    Optional<Route> deleteRoute(int id);
}
