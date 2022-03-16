package ITSOL.Bus.Management.Full.Stack.Service;

import ITSOL.Bus.Management.Full.Stack.Utility.ObjectValidator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;


public abstract class AbstractService {
    @Autowired
    protected Environment env;

    protected ObjectMapper objectMapper;

    @Autowired
    protected ObjectValidator objectValidator;

    @PostConstruct
    public void init(){
        objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
    }
}
