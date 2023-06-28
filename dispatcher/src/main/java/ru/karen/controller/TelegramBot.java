package ru.karen.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;


@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {

    @Value("${bot.name}")
    private String name;
    @Autowired
    private  UpdateController controller;

    public TelegramBot(@Value("${bot.token}") String token) {
        super(token);
    }
    @PostConstruct
    public void init() {
        controller.registerBot(this);
    }

    @Override
    public String getBotUsername() {
        return name;
    }

    @Override
    public void onUpdateReceived(Update update) {
        controller.processUpdate(update);
    }

    public void sendMessage(SendMessage message) {
        if(message != null) {
            try {
                execute(message);
            }catch (TelegramApiException e) {
                log.error(String.valueOf(e));
            }
        }
    }
}
