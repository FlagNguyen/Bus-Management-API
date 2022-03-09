package ITSOL.Bus.Management.Full.Stack.Exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String mess){
        super(mess);
    }
}
