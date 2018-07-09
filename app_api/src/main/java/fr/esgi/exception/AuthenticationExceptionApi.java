package fr.esgi.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(HttpStatus.FORBIDDEN)
public class AuthenticationExceptionApi extends RuntimeException  {
    public AuthenticationExceptionApi(String message) {
        super(message);
    }
}
