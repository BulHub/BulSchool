package ru.itis.repositories;

import org.springframework.stereotype.Component;
import ru.itis.models.Message;

import java.util.List;

@Component
public interface ChatRepository extends CrudRepository<Long, Message> {
    List<Message> findByEmail(String email);
}
