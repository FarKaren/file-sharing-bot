package ru.karen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.karen.entity.AppDocument;

public interface AppDocumentRepository extends JpaRepository<AppDocument, Long> {
}
