package org.tisha.tmajorbot;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.Assert;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import org.tisha.tmajorbot.bot.Bot;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class TMajorBotApplication
{

    public static void main( String[] args )
    {
        Assert.isTrue( args.length > 0, () -> "Bot token expected!" );
        Assert.notNull( args[0], () -> "Bot token expected!" );

        String botToken = args[0];

        SpringApplication.run( TMajorBotApplication.class, args );

        try
        {
            // You can use your own BotSession implementation if needed.
            TelegramBotsApi botsApi = new TelegramBotsApi( DefaultBotSession.class );
            botsApi.registerBot( new Bot( botToken ) );
        }
        catch( TelegramApiException e )
        {
            log.error( e.getMessage(), e );
        }

    }

}
