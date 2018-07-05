package fr.esgi.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class IOExceptionApi extends RuntimeException{
    public IOExceptionApi(String message) {
        super(message);
    }
}
