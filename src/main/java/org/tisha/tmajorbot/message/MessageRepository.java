package org.tisha.tmajorbot.message;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer>
{
    List<Message> findAllByTimestampIsBefore( LocalDateTime timestamp );
}
