package ru.karen.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.karen.entity.AppDocument;
import ru.karen.entity.AppPhoto;
import ru.karen.repository.AppDocumentRepository;
import ru.karen.repository.AppPhotoRepository;
import ru.karen.service.FileService;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final AppDocumentRepository appDocumentRepository;
    private final AppPhotoRepository appPhotoRepository;
    private final CryptoTool cryptoTool;


    @Override
    public AppDocument getDocument(String hash) {
        var id = cryptoTool.idOf(hash);
        if (id == null) {
            return null;
        }
        return appDocumentRepository.findById(id).orElse(null);
    }

    @Override
    public AppPhoto getPhoto(String hash) {
        var id = cryptoTool.idOf(hash);
        if (id == null) {
            return null;
        }
        return appPhotoRepository.findById(id).orElse(null);
    }
}
