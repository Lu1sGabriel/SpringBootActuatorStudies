package dev.luis.goes.spring.actuator.studies.rating.application.useCase;

import dev.luis.goes.spring.actuator.studies.product.domain.entity.ProductEntity;
import dev.luis.goes.spring.actuator.studies.product.infrastructure.repository.ProductRepository;
import dev.luis.goes.spring.actuator.studies.rating.domain.entity.RatingEntity;
import dev.luis.goes.spring.actuator.studies.rating.infrastructure.repository.RatingRepository;
import dev.luis.goes.spring.actuator.studies.rating.presentation.dto.reponse.RatingResponse;
import dev.luis.goes.spring.actuator.studies.rating.presentation.dto.request.RatingRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RatingCreateUseCase {

    private final RatingRepository ratingRepository;
    private final ProductRepository productRepository;

    public RatingResponse create(RatingRequest request) {
        ProductEntity product = findProductById(request.productId());

        RatingEntity rating = ratingRepository.save(
                new RatingEntity(request.text(), request.rating(), product)
        );

        product.addRating(rating);

        return new RatingResponse(
                rating.getId(),
                rating.getText(),
                rating.getRating(),
                product.getId(),
                product.getName(),
                product.getPrice()
        );
    }

    private ProductEntity findProductById(UUID id) {
        if (id == null) throw new IllegalArgumentException("The ID provided must not be null.");

        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with the given ID."));
    }

}