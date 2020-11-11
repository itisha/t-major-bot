package org.tisha.tmajorbot;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import org.tisha.tmajorbot.bot.TMajorBot;

import javax.annotation.PostConstruct;

@Slf4j
@EnableScheduling
@SpringBootApplication
@RequiredArgsConstructor
public class TMajorBotApplication
{
    private final TMajorBot bot;

    public static void main( String[] args )
    {
        SpringApplication.run( TMajorBotApplication.class, args );
    }

    @PostConstruct
    public void init()
    {
        log.info( "Context initialized. Starting Bot." );
        try
        {
            // You can use your own BotSession implementation if needed.
            TelegramBotsApi botsApi = new TelegramBotsApi( DefaultBotSession.class );
            botsApi.registerBot( bot );
        }
        catch( TelegramApiException e )
        {
            log.error( e.getMessage(), e );
        }
    }

}
