package fr.esgi.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(HttpStatus.CONFLICT)
public class CategoryAlreadyExistExceptionApi extends RuntimeException  {
    public CategoryAlreadyExistExceptionApi(String message) {
        super(message);
    }
}