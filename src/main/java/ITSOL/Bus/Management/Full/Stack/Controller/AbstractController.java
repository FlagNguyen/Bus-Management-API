package ITSOL.Bus.Management.Full.Stack.Controller;

import ITSOL.Bus.Management.Full.Stack.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public abstract class AbstractController<s> {

    @Autowired
    protected s service;

    protected <T> ResponseEntity<T> response(Optional<T> response){
        return new ResponseEntity<T>(response.orElseThrow(() -> {
            throw new ResourceNotFoundException("Can't found resource");
        }), HttpStatus.OK);
    }
}
