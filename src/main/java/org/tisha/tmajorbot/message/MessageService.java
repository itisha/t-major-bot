package org.tisha.tmajorbot.message;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class MessageService
{
    private final MessageRepository messageRepository;

    public Collection<Message> findAllOlderThan( Long messageLifetimeMinutes )
    {
        return messageRepository.findAllByTimestampIsBefore( LocalDateTime.now().minusMinutes( messageLifetimeMinutes ) );
    }

    public void removeAll( Collection<Message> messages )
    {
        messageRepository.deleteAll( messages );
    }

    public Message save( Message message )
    {
        return messageRepository.save( message );
    }
}
