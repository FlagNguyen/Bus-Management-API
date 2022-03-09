package ITSOL.Bus.Management.Full.Stack.Controller;

import ITSOL.Bus.Management.Full.Stack.DTO.Request.RouteRequest;
import ITSOL.Bus.Management.Full.Stack.Service.RouteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/route")
public class RouteController extends AbstractController<RouteService>{

    @GetMapping(path = "")
    public ResponseEntity<?> getAllRoute(){
        return response(service.getAllRoute());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getRouteById(@PathVariable int id){
        return response(service.getRouteById(id));
    }

    @PostMapping
    public ResponseEntity<?> addRoute(@RequestBody RouteRequest routeRequest){
        return response(service.addRoute(routeRequest));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateRoute(@RequestBody RouteRequest routeRequest, @PathVariable int id){
        return response(service.updateRoute(routeRequest,id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deletedRoute(@PathVariable int id){
        return response(service.deleteRoute(id));
    }
}
