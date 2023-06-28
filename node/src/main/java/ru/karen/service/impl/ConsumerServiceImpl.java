package ru.karen.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.karen.service.ConsumerService;
import ru.karen.service.ProducerService;

@Service
@Slf4j
@RequiredArgsConstructor
@EnableRabbit
public class ConsumerServiceImpl implements ConsumerService {
    private final ProducerService producerService;

    @Override
    @RabbitListener(queues = "text_message_update", ackMode = "AUTO")
    public void consumeTextMessageUpdates(Update update) {
        log.debug("Node text message is received");

        var message = update.getMessage();
        var sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText("Hello from node");
        producerService.producerAnswer(sendMessage);

    }

    @Override
    @RabbitListener(queues = "doc_message_update", ackMode = "AUTO")
    public void consumeDocMessageUpdates(Update update) {
        log.debug("Node doc message is received");
    }

    @Override
    @RabbitListener(queues = "photo_message_update", ackMode = "AUTO")
    public void consumePhotoMessageUpdates(Update update) {
        log.debug("Node photo message is received");
    }
}
