package fr.esgi.components.validation.dto;

import fr.esgi.components.validation.MessageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    private String message;
    private MessageType type;
}
