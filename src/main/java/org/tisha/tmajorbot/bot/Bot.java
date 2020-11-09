package org.tisha.tmajorbot.bot;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Objects;

@Slf4j
public class Bot extends TelegramLongPollingBot
{

    private final String token;

    public Bot( String token )
    {
        this.token = token;
    }

    @Override
    public void onUpdateReceived( Update update )
    {
        // We check if the update has a message and the message has text
        if( update.hasMessage() && update.getMessage().hasText() )
        {
            SendMessage message = SendMessage.builder() // Create a SendMessage object with mandatory fields
                                             .chatId( Objects.toString( update.getMessage().getChatId() ) )
                                             .text( update.getMessage().getText() )
                                             .build();
            try
            {
                execute( message ); // Call method to send the message
            }
            catch( TelegramApiException e )
            {
                log.error( e.getMessage(), e );
            }
        }
    }

    @Override
    public String getBotUsername()
    {
        return "t_mayor_bot";
    }

    @Override
    public String getBotToken()
    {
        return token;
    }
}