package ru.karen.service;

import org.telegram.telegrambots.meta.api.objects.Message;
import ru.karen.entity.AppDocument;
import ru.karen.entity.AppPhoto;
import ru.karen.service.enums.LinkType;

public interface FileService {
    AppDocument processDoc(Message telegramMessage);
    AppPhoto processPhoto(Message telegramMessage);
    String generateLink(Long docId, LinkType linkType);
}
