package ru.karen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.karen.entity.RawData;
@Repository
public interface RawDataRepository extends JpaRepository<RawData, Long> {
}
