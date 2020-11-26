package org.tisha.tmajorbot.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity( name = "message" )
public class Message
{

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;

    private Long chatId;

    private Integer messageId;

    private LocalDateTime timestamp;

    private String text;

}
