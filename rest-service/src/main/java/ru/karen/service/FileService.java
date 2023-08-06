package ru.karen.service;

import ru.karen.entity.AppDocument;
import ru.karen.entity.AppPhoto;

public interface FileService {
    AppDocument getDocument(String id);
    AppPhoto getPhoto(String id);
}
