package dev.luis.goes.spring.actuator.studies.product.application.useCase;

import dev.luis.goes.spring.actuator.studies.product.domain.entity.ProductEntity;
import dev.luis.goes.spring.actuator.studies.product.infrastructure.repository.ProductRepository;
import dev.luis.goes.spring.actuator.studies.product.presentation.dtos.response.ProductResponse;
import dev.luis.goes.spring.actuator.studies.rating.domain.entity.RatingEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductGetByIdUseCase {

    private final ProductRepository productRepository;

    public ProductResponse get(final UUID id) {
        if (id == null) throw new IllegalArgumentException("The provided ID must not be null");

        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with the given ID."));

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                createRatingList(product),
                calculateAverage(product)
        );
    }

    private List<ProductResponse.Rating> createRatingList(ProductEntity product) {
        return product.getRatings().stream().map(
                        rating -> new ProductResponse.Rating(
                                rating.getId(),
                                rating.getText(),
                                rating.getRating()
                        ))
                .toList();
    }

    private Double calculateAverage(ProductEntity product) {
        return product.getRatings().stream()
                .mapToDouble(RatingEntity::getRating)
                .average()
                .orElse(0.0);
    }

}