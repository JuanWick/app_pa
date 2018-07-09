package fr.esgi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestExceptionApi extends RuntimeException {

    public BadRequestExceptionApi(String message) {
        super(message);
    }

    public BadRequestExceptionApi(String message, Throwable cause) {
        super(message, cause);
    }
}
