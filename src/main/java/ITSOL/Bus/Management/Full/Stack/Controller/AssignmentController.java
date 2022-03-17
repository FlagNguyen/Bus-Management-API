package ITSOL.Bus.Management.Full.Stack.Controller;

import ITSOL.Bus.Management.Full.Stack.DTO.Request.AssignmentRequest;
import ITSOL.Bus.Management.Full.Stack.Service.AssignmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/assignment")
public class AssignmentController extends AbstractController<AssignmentService> {

    @GetMapping
    public ResponseEntity<?> getAllRoster() {
        return response(service.getAllRoster());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getRosterByID(@PathVariable int id){
        return response(service.getRosterByID(id));
    }

    @PostMapping
    public ResponseEntity<?> assign(@RequestBody AssignmentRequest assignmentRequest){
        return response(service.assign(assignmentRequest));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateAssign(@RequestBody AssignmentRequest assignmentRequest,@PathVariable int id){
        return response(service.updateAssign(assignmentRequest,id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteAssign(@RequestBody int id){
        return response(service.deleteAssign(id));
    }

}
