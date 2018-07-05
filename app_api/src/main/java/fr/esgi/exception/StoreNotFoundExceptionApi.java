package fr.esgi.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(HttpStatus.NOT_FOUND)
public class StoreNotFoundExceptionApi extends RuntimeException  {
    public StoreNotFoundExceptionApi(String message) {
        super(message);
    }
}
