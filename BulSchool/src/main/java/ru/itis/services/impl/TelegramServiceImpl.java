package ru.itis.services.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.itis.models.Feedback;
import ru.itis.services.TelegramService;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

@Service
public class TelegramServiceImpl implements TelegramService {
    private static final Logger log = Logger.getLogger(TelegramServiceImpl.class);

    @Override
    public void sendMessageForFeedback(Feedback feedback){
        String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";
        String apiToken = "1228560165:AAGTUrCaDrVyW-d7OXM1tWhkfJ8IUC4uF4c";
        String chatId = "@feedback_for_BulSchool";
        String text = feedback.toString();
        urlString = String.format(urlString, apiToken, chatId, text);
        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            StringBuilder sb = new StringBuilder();
            InputStream is = new BufferedInputStream(conn.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String inputLine = "";
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
            log.info("Sending a message in telegram was successful! ID: " + feedback.getId());
        }catch (IOException ex){
            log.error("Failed to send telegram message! " + feedback);
        }
    }
}
