package sg.edu.nus.iss.paf_day25_wsA.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sg.edu.nus.iss.paf_day25_wsA.model.exceptions.UnableToInsertOrderDetailException;
import sg.edu.nus.iss.paf_day25_wsA.model.exceptions.UnableToInsertOrderException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException(Exception ex, HttpServletRequest request, HttpServletResponse response) {

        ex.printStackTrace();

        ErrorMessage message = new ErrorMessage();
            message.setStatus(500);
            message.setMessage(ex.getMessage());
            message.setTimeStamp(new Date());
            message.setEndPoint(request.getRequestURI());

        return new ResponseEntity<ErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler({UnableToInsertOrderException.class, UnableToInsertOrderDetailException.class})
    public ResponseEntity<ErrorMessage> handleUnableToInsertExceptions(Exception ex, HttpServletRequest request) {

        ex.printStackTrace();

        ErrorMessage message = new ErrorMessage();
            message.setStatus(500);
            message.setMessage(ex.getMessage());
            message.setTimeStamp(new Date());
            message.setEndPoint(request.getRequestURI());

        return new ResponseEntity<ErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorMessage> handleNoHandlerFoundException(NoResourceFoundException ex, HttpServletRequest request) {

        ErrorMessage message = new ErrorMessage();
        message.setStatus(HttpStatus.NOT_FOUND.value());
        message.setMessage("Page not found");
        message.setTimeStamp(new Date());
        message.setEndPoint(request.getRequestURI());

        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}
