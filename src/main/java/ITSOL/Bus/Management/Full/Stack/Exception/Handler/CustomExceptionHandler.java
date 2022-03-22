package ITSOL.Bus.Management.Full.Stack.Exception.Handler;

import ITSOL.Bus.Management.Full.Stack.DTO.Response.ErrorResponse;
import ITSOL.Bus.Management.Full.Stack.Exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException e){
        return new ResponseEntity<>(new ErrorResponse("000","Can't found resource"),null, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({InvalidLevelFormatException.class})
    public ResponseEntity<ErrorResponse> handleInvalidLevelFormatException(InvalidLevelFormatException e){
        return new ResponseEntity<>(new ErrorResponse("001","Level must character A-F"),null,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InvalidPhoneNumberFormatException.class})
    public ResponseEntity<ErrorResponse> handleInvalidPhoneNumberFormatException(InvalidPhoneNumberFormatException e){
        return new ResponseEntity<>(new ErrorResponse("002","Invalid Phone Number Format"),null,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InvalidRequestException.class})
    public ResponseEntity<ErrorResponse> handleInvalidRequestException(InvalidRequestException e){
        return new ResponseEntity<>(new ErrorResponse("003","Invalid Request Format"),null,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InvalidIntegerFormat.class})
    public ResponseEntity<ErrorResponse> handleInvalidIntegerFormat(InvalidIntegerFormat e){
        return new ResponseEntity<>(new ErrorResponse("004","Invalid Integer Format"),null,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InvalidFloatFormat.class})
    public ResponseEntity<ErrorResponse> handleInvalidFloatFormat(InvalidFloatFormat e){
        return new ResponseEntity<>(new ErrorResponse("005","Invalid Float Format"),null,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NotFoundIDException.class})
    public ResponseEntity<ErrorResponse> handleNotFoundIDException(NotFoundIDException e){
        return new ResponseEntity<>(new ErrorResponse("006","This id not available"),null,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ExceedTurnException.class})
    public ResponseEntity<ErrorResponse> handleExceedTurnException(ExceedTurnException e){
        return new ResponseEntity<>(new ErrorResponse("007","Exceed Total Turn"),null,HttpStatus.BAD_REQUEST);
    }
}
