package org.tisha.tmajorbot.message;

import java.util.Collection;

public interface MessageRepository
{
    Collection<Message> findAllOlderThan( Long durationMinutes );

    Collection<Message> findAll();

    void removeAll( Collection<Message> messages );

    void add( Message message );
}
