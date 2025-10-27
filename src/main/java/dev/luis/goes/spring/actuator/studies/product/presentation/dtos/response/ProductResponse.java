package dev.luis.goes.spring.actuator.studies.product.presentation.dtos.response;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record ProductResponse(
        UUID id,
        String name,
        BigDecimal price,
        List<Rating> rating,
        Double average
) {
    public ProductResponse(UUID id, String name, BigDecimal price) {
        this(id, name, price, null, null);
    }

    public record Rating(UUID id, String text, Double rating) {
    }

}