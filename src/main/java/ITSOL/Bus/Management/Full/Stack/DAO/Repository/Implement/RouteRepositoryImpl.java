package ITSOL.Bus.Management.Full.Stack.DAO.Repository.Implement;

import ITSOL.Bus.Management.Full.Stack.DAO.Entity.Route;
import ITSOL.Bus.Management.Full.Stack.DAO.Repository.AbstractRepository;
import ITSOL.Bus.Management.Full.Stack.DAO.Repository.RouteRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RouteRepositoryImpl extends AbstractRepository implements RouteRepository {

    @Override
    public Optional<List<Route>> getAllRoute() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * ");
        sql.append("FROM ROUTE ");
        sql.append("WHERE isdeleted = 0 ");
        List<Route> routes = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(Route.class));
        return Optional.ofNullable(routes);
    }

    @Override
    public Optional<List<Route>> getTotalRoute() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * ");
        sql.append("FROM ROUTE ");
        List<Route> routes = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(Route.class));
        return Optional.ofNullable(routes);
    }

    @Override
    public Optional<Route> getRouteById(int id) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * ");
        sql.append("FROM ROUTE ");
        sql.append("WHERE isdeleted = 0 AND route_id = ?");
        List<Route> route = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(Route.class), id);
        if(route.size() == 0){
            return Optional.empty();
        }
        return Optional.ofNullable(route.get(0));
    }

    @Override
    public Optional<Route> addRoute(Route route) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ROUTE");
        sql.append("(route_id,distance,stations,isdeleted) " +
                "VALUES (?,?,?,0)");
        jdbcTemplate.update(sql.toString(),route.getRoute_ID(),route.getDistance(),route.getStations());
        return getRouteById(route.getRoute_ID());
    }

    @Override
    public Optional<Route> updateRoute(Route route, int id) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ROUTE ");
        sql.append(" SET distance = ? , stations = ? ");
        sql.append(" WHERE route_id = ?");
        jdbcTemplate.update(sql.toString(), route.getDistance(), route.getStations(), id);
        return getRouteById(id);
    }

    @Override
    public Optional<Route> deleteRoute(int id) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ROUTE ");
        sql.append(" SET isdeleted = 1 ");
        sql.append(" WHERE route_id = ?");
        jdbcTemplate.update(sql.toString(), id);
        return Optional.empty();
    }
}
