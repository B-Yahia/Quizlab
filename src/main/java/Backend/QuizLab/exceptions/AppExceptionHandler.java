package Backend.QuizLab.exceptions;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AppExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public Map<String,String> handleNotFoundEx (ResourceNotFoundException exception){
        Map<String, String> errorMsg = new HashMap<>();
        errorMsg.put("message", exception.getMessage());
        return  errorMsg;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleInvalidData (MethodArgumentNotValidException exception){
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return errors;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataAccessException.class)
    public Map<String, String> handleDataAccessException(DataAccessException exception) {
        Map<String, String> errorMsg = new HashMap<>();
        errorMsg.put("message", exception.getMessage());
        return errorMsg;
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Map<String, String> handleMethodNotSupported(HttpRequestMethodNotSupportedException exception) {
        Map<String, String> errorMsg = new HashMap<>();
        errorMsg.put("message", "HTTP method not supported for this endpoint");
        return errorMsg;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Map<String, String> handleMessageNotReadable(HttpMessageNotReadableException exception) {
        Map<String, String> errorMsg = new HashMap<>();
        errorMsg.put("message", "Invalid JSON format in request body");
        return errorMsg;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Map<String, String> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException exception) {
        Map<String, String> errorMsg = new HashMap<>();
        errorMsg.put("message", "Invalid parameter type for: " + exception.getName());
        return errorMsg;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Map<String, String> handleAllExceptions(Exception exception) {
        Map<String, String> errorMsg = new HashMap<>();
         errorMsg.put("message", exception.getMessage());
         errorMsg.put("error", exception.getClass().getSimpleName());
        return errorMsg;
    }

}
