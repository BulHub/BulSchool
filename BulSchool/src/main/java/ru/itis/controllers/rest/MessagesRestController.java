package ru.itis.controllers.rest;

import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.MessageDto;
import ru.itis.services.ChatService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class MessagesRestController {
    private static ConcurrentHashMap<String, List<MessageDto>> messages = new ConcurrentHashMap<>();

    private final ChatService chatService;

    public MessagesRestController(ChatService chatService) {
        this.chatService = chatService;
    }

    // получили сообщение от какой либо страницы - мы его разошлем во все другие страницы
    @PostMapping(path = "/messages", consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<Object> receiveMessage(@RequestBody MessageDto message, HttpSession session) {
        String email = (String) session.getAttribute("email");
        // если сообщений с этой или для этой страницы еще не было
        if (!messages.containsKey(email)) {
            // добавляем эту страницу в Map-у страниц
            messages.put(email, new ArrayList<>());
        }
        // полученное сообщение добавляем для всех открытых страниц нашего приложения
        for (List<MessageDto> pageMessages : messages.values()) {
            // перед тем как положить сообщение для какой-либо страницы
            // мы список сообщений блокируем
            synchronized (pageMessages) {
                // добавляем сообщение
                pageMessages.add(message);
                chatService.add(message.toMessage());
                // говорим, что другие потоки могут воспользоваться этим списком
                pageMessages.notifyAll();
            }
        }

        return ResponseEntity.ok().build();
    }

    // получить все сообщения для текущего запроса
    @SneakyThrows
    @GetMapping("/messages")
    public ResponseEntity<List<MessageDto>> getMessagesForPage(@RequestParam("email") String email) {
        // получили список сообшений для страницы и заблокировали его
        synchronized (messages.get(email)) {
            // если нет сообщений уходим в ожидание
            if (messages.get(email).isEmpty()) {
                messages.get(email).wait();
            }
        }

        // если сообщения есть - то кладем их в новых список
        List<MessageDto> response = new ArrayList<>(messages.get(email));
        // удаляем сообщения из мапы
        messages.get(email).clear();
        return ResponseEntity.ok(response);
    }
}