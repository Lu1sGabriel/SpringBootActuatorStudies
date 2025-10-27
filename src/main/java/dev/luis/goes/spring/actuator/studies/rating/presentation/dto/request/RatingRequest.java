package dev.luis.goes.spring.actuator.studies.rating.presentation.dto.request;

import jakarta.validation.constraints.*;

import java.util.UUID;

public record RatingRequest(

        @NotBlank
        @Size(min = 5)
        String text,

        @NotNull
        @Positive
        @Min(0)
        @Max(5)
        Double rating,

        @NotNull
        UUID productId
) {
}