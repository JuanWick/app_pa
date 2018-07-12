package fr.esgi.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(HttpStatus.FORBIDDEN)
public class CategoryCreationExceptionApi extends RuntimeException  {
    public CategoryCreationExceptionApi(String message) {
        super(message);
    }
}