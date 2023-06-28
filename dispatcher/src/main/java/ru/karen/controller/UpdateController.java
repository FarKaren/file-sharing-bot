package ru.karen.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.karen.config.RabbitConfigurationProperty;
import ru.karen.service.UpdateProducer;
import ru.karen.utils.MessageUtils;



@Controller
@Slf4j
@RequiredArgsConstructor
public class UpdateController {

    private final MessageUtils messageUtils;
    private final UpdateProducer updateProducer;
    private final RabbitConfigurationProperty configurationProperty;
    private TelegramBot bot;

    public void registerBot(TelegramBot bot) {
        this.bot = bot;
    }

    public void processUpdate(Update update) {
        if(update == null) {
            log.error("Update is null");
            return;
        }
        if(update.getMessage() != null)
            distributeMessagesByType(update);
        else log.error("Unsupported messages type {}", update);
    }

    private void distributeMessagesByType(Update update) {
        var message = update.getMessage();
        if(message.getText() != null){
            processTestMessage(update);
        }else if (message.getDocument() != null) {
            processDocMessage(update);
        }else if (message.getPhoto() != null) {
            processPhotoMessage(update);
        } else setUnsupportedMessageTypeView(update);
    }

    private void processPhotoMessage(Update update) {
        updateProducer.produce(configurationProperty.getPhotoMessageUpdate(), update);
        setFileIsReceivedView(update);
    }

    private void setFileIsReceivedView(Update update) {
        var message = messageUtils.generateSendMessageWithText(update, "File is received. Handling...");
        setView(message);
    }

    private void processDocMessage(Update update) {
        updateProducer.produce(configurationProperty.getDocMessageUpdate(), update);
        setFileIsReceivedView(update);
    }

    private void processTestMessage(Update update) {
        updateProducer.produce(configurationProperty.getTextMessageUpdate(), update);
        setFileIsReceivedView(update);
    }

    private void setUnsupportedMessageTypeView(Update update) {
        var message = messageUtils.generateSendMessageWithText(update,
                "Unsupported message type");
        setView(message);
    }

    public void setView(SendMessage message) {
        bot.sendMessage(message);
    }
}
