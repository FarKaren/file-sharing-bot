package ru.karen.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rabbitmq.queues")
@Getter
@Setter
public class RabbitConfigurationProperty {
    private String docMessageUpdate;
    private String photoMessageUpdate;
    private String textMessageUpdate;
    private String answerMessage;
}
