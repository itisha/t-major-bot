package org.tisha.tmajorbot.batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.tisha.tmajorbot.bot.TMajorBot;
import org.tisha.tmajorbot.message.Message;
import org.tisha.tmajorbot.message.MessageService;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

@Component
@Slf4j
@RequiredArgsConstructor
public class ScheduledTasks
{
    private final MessageService messageService;
    private final TMajorBot bot;

    @Value( "${telegram.message.lifetime.minutes}" )
    private Long messageLifetimeMinutes;

    @Scheduled( cron = "${telegram.message.purge.cron}" )
    public void deleteOldMessages()
    {
        log.info( "Cleaning messages {} minutes later than {}", messageLifetimeMinutes, LocalDateTime.now() );

        Collection<Message> messages = messageService.findAllOlderThan( messageLifetimeMinutes );
        log.info( "Found {} messages", messages.size() );

        messages.forEach( msg -> {
            try
            {
                bot.execute(
                    DeleteMessage
                        .builder()
                        .chatId( Objects.toString( msg.getChatId() ) )
                        .messageId( msg.getMessageId() )
                        .build() );
            }
            catch( TelegramApiException e )
            {
                log.error( "Error when deleting messages chatId = {}, messageId = {}", msg.getChatId(), msg.getMessageId(), e );
            }
        } );
        messageService.removeAll( messages );
    }
}
