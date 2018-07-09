package fr.esgi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RoleNotFoundExceptionApi extends RuntimeException {
    public RoleNotFoundExceptionApi(String message) {
        super(message);
    }

    public RoleNotFoundExceptionApi(String message, Throwable cause) {
        super(message, cause);
    }
}
