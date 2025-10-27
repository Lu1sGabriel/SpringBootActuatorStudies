package dev.luis.goes.spring.actuator.studies.rating.application.useCase;

import dev.luis.goes.spring.actuator.studies.rating.domain.entity.RatingEntity;
import dev.luis.goes.spring.actuator.studies.rating.infrastructure.repository.RatingRepository;
import dev.luis.goes.spring.actuator.studies.rating.presentation.dto.reponse.RatingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingGetAllUseCase {

    private final RatingRepository ratingRepository;

    public Page<RatingResponse> get(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("createdAt").descending());
        Page<RatingEntity> ratings = ratingRepository.findAll(pageable);

        return ratings.map(rating -> new RatingResponse(
                rating.getId(),
                rating.getText(),
                rating.getRating(),
                rating.getProduct().getId(),
                rating.getProduct().getName(),
                rating.getProduct().getPrice()
        ));
    }

}