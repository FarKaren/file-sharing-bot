package ru.karen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.karen.entity.AppPhoto;

public interface AppPhotoRepository extends JpaRepository<AppPhoto, Long> {
}

