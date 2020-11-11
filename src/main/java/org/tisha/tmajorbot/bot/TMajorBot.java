package org.tisha.tmajorbot.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.tisha.tmajorbot.message.Message;
import org.tisha.tmajorbot.message.MessageRepository;

import java.time.LocalDateTime;

@Slf4j
@Component
public class TMajorBot extends TelegramLongPollingBot
{

    private final MessageRepository messageRepository;
    private final String botUsername;
    private final String botToken;

    public TMajorBot( MessageRepository messageRepository,
        @Value( "${bot.username}" ) String botUsername,
        @Value( "${bot.token}" ) String botToken )
    {
        this.messageRepository = messageRepository;
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

            messageRepository.add( new Message( chatId, messageId, LocalDateTime.now() ) );
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