package fr.esgi.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
public class LocalizationExceptionApi extends RuntimeException{
    public LocalizationExceptionApi(String message) {
        super(message);
    }
}
