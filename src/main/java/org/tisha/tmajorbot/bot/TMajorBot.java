package org.tisha.tmajorbot.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.tisha.tmajorbot.message.Message;
import org.tisha.tmajorbot.message.MessageService;

import java.time.LocalDateTime;

@Slf4j
@Component
public class TMajorBot extends TelegramLongPollingBot
{

    private final MessageService messageService;
    private final String botUsername;
    private final String botToken;

    public TMajorBot( MessageService messageService,
        @Value( "${bot.username}" ) String botUsername,
        @Value( "${bot.token}" ) String botToken )
    {
        this.messageService = messageService;
        this.botUsername = botUsername;
        this.botToken = botToken;
    }

    @Override
    public void onUpdateReceived( Update update )
    {
        if( update.hasMessage() )
        {
            Long chatId = update.getMessage().getChatId();
            Integer messageId = update.getMessage().getMessageId();
            String text = update.getMessage().getText();

            messageService.save( new Message( null, chatId, messageId, LocalDateTime.now(), text ) );
        }
    }

    @Override
    public String getBotUsername()
    {
        return botUsername;
    }

    @Override
    public String getBotToken()
    {
        return botToken;
    }
}