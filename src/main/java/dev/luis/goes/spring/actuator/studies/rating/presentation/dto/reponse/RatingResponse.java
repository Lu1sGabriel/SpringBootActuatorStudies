package dev.luis.goes.spring.actuator.studies.rating.presentation.dto.reponse;

import java.math.BigDecimal;
import java.util.UUID;

public record RatingResponse(
        UUID id,
        String text,
        Double rating,
        Product product
) {
    public RatingResponse(UUID id, String text, Double rating,
                          UUID productId, String productName, BigDecimal productPrice) {
        this(id, text, rating, new Product(productId, productName, productPrice));
    }

    private record Product(
            UUID id,
            String name,
            BigDecimal price
    ) {
    }

}