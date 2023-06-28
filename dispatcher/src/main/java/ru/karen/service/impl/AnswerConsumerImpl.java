package ru.karen.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.karen.controller.UpdateController;
import ru.karen.service.AnswerConsumer;
@Service
@Slf4j
@RequiredArgsConstructor
public class AnswerConsumerImpl implements AnswerConsumer {
    private final UpdateController updateController;
    @Override
    @RabbitListener(queues = "answer_message")
    public void consume(SendMessage sendMessage) {
        updateController.setView(sendMessage);
    }
}
