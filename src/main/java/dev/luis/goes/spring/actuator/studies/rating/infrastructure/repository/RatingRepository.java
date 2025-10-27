package dev.luis.goes.spring.actuator.studies.rating.infrastructure.repository;

import dev.luis.goes.spring.actuator.studies.rating.domain.entity.RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RatingRepository extends JpaRepository<RatingEntity, UUID> {
}