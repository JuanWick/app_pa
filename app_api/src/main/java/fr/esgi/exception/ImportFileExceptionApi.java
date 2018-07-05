package fr.esgi.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
public class ImportFileExceptionApi extends RuntimeException{
    public ImportFileExceptionApi(String message) {
        super(message);
    }
}
