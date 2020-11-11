package org.tisha.tmajorbot.message;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Message
{
    private Long chatId;
    private Integer messageId;
    private LocalDateTime timestamp;
}
