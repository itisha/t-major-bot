package org.tisha.tmajorbot.message;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MessageRepositoryImpl implements MessageRepository
{

    List<Message> messages = new LinkedList<>();

    @Override
    public Collection<Message> findAllOlderThan( Long durationMinutes )
    {
        LocalDateTime now = LocalDateTime.now();
        return messages
            .stream()
            .filter( message -> ChronoUnit.MINUTES.between( message.getTimestamp(), now ) >= durationMinutes )
            .collect( Collectors.toList() );
    }

    @Override
    public Collection<Message> findAll()
    {
        return messages;
    }

    @Override
    public void removeAll( Collection<Message> messages )
    {
        findAll().removeAll( messages );
    }

    @Override
    public void add( Message message )
    {
        if( message != null )
        {
            messages.add( message );
        }
    }
}
