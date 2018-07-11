package fr.esgi.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(HttpStatus.FORBIDDEN)
public class RgpdDeclinedExceptionApi extends RuntimeException{
    public RgpdDeclinedExceptionApi(String message) {
        super(message);
    }
}
