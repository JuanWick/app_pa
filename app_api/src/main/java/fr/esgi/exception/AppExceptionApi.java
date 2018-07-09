package fr.esgi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class AppExceptionApi extends RuntimeException {
    public AppExceptionApi(String message) {
        super(message);
    }

    public AppExceptionApi(String message, Throwable cause) {
        super(message, cause);
    }
}
