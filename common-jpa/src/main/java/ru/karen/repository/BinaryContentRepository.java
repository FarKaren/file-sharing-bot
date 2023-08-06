package ru.karen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.karen.entity.BinaryContent;

public interface BinaryContentRepository extends JpaRepository<BinaryContent, Long> {
}
