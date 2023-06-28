package ru.karen.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(RabbitConfigurationProperty.class)
@RequiredArgsConstructor
public class RabbitConfig {

    private final RabbitConfigurationProperty configurationProperty;
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue textMessageQueue() {
        return new Queue(configurationProperty.getTextMessageUpdate());
    }

    @Bean
    public Queue docMessageQueue() {
        return new Queue(configurationProperty.getDocMessageUpdate());
    }

    @Bean
    public Queue photoMessageQueue() {
        return new Queue(configurationProperty.getPhotoMessageUpdate());
    }

    @Bean
    public Queue answerMessageQueue() {
        return new Queue(configurationProperty.getAnswerMessage());
    }


}
