package org.tisha.tmajorbot.message;

import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.time.LocalDateTime;
import java.util.Collection;

@NoArgsConstructor
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
class MessageServiceTest
{
    @LocalServerPort
    int randomServerPort;

    @Autowired
    MessageService messageService;

    @Test
    void test()
    {
        Long chatId = 1L;

        LocalDateTime minutesAgo_1 = LocalDateTime.now().minusMinutes( 1 );
        LocalDateTime minutesAgo_5 = LocalDateTime.now().minusMinutes( 5 );
        LocalDateTime minutesAgo_15 = LocalDateTime.now().minusMinutes( 5 );

        messageService.save( getMessage( chatId, 1, minutesAgo_1 ) );
        messageService.save( getMessage( chatId, 2, minutesAgo_5 ) );
        messageService.save( getMessage( chatId, 3, minutesAgo_15 ) );
        messageService.save( getMessage( chatId, 3, LocalDateTime.now() ) );

        Collection<Message> allOlderThan = messageService.findAllOlderThan( 1L );
        Assertions.assertEquals( 3, allOlderThan.size() );
    }

    private Message getMessage( Long chatId, Integer messageId, LocalDateTime timestamp )
    {
        return Message.builder()
                      .chatId( chatId )
                      .messageId( messageId )
                      .timestamp( timestamp )
                      .build();
    }
}