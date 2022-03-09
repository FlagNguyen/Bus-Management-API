package ITSOL.Bus.Management.Full.Stack.Controller;

import ITSOL.Bus.Management.Full.Stack.DTO.Request.DriverRequest;
import ITSOL.Bus.Management.Full.Stack.Service.DriverService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "drivers")
public class DriverController extends AbstractController<DriverService>{

    @GetMapping(path = "")
    public ResponseEntity<?> getAll(){
        return response(service.getAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getDriverById(@PathVariable int id){
        return response(service.getDriverById(id));
    }

    @PostMapping
    public ResponseEntity<?> addDriver(@RequestBody DriverRequest driverRequest){
        return response(service.addDriver(driverRequest));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateDriver(@RequestBody DriverRequest driverRequest, @PathVariable int id){
        return response(service.updateDriver(driverRequest,id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deletedDriver(@PathVariable int id){
        return response(service.deleteDriver(id));
    }
}
