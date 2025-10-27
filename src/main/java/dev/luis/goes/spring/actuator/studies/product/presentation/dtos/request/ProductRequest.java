package dev.luis.goes.spring.actuator.studies.product.presentation.dtos.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank
        @Size(min = 5)
        String name,

        @NotNull
        @Positive
        @Min(0)
        BigDecimal price
) {
}