package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.Message;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDto {
    private String email;
    private String text;

    public Message toMessage() {
        Message message = new Message();
        message.setEmail(email);
        message.setText(text);
        return message;
    }

    public static MessageDto fromMessage(Message message) {
        return MessageDto.builder()
                .email(message.getEmail())
                .text(message.getText())
                .build();
    }
}
