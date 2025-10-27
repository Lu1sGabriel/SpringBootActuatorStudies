package dev.luis.goes.spring.actuator.studies.rating.presentation.controller;

import dev.luis.goes.spring.actuator.studies.rating.application.useCase.RatingCreateUseCase;
import dev.luis.goes.spring.actuator.studies.rating.application.useCase.RatingGetAllUseCase;
import dev.luis.goes.spring.actuator.studies.rating.presentation.dto.reponse.RatingResponse;
import dev.luis.goes.spring.actuator.studies.rating.presentation.dto.request.RatingRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/ratings")
@RequiredArgsConstructor
public class RatingController {

    private final RatingGetAllUseCase getAllUseCase;
    private final RatingCreateUseCase createUseCase;

    @GetMapping
    public ResponseEntity<Page<RatingResponse>> getAll(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                                       @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        Page<RatingResponse> responsePage = getAllUseCase.get(page, pageSize);
        return ResponseEntity.ok().body(responsePage);
    }

    @PostMapping
    public ResponseEntity<RatingResponse> create(@RequestBody @Valid RatingRequest request) {
        RatingResponse response = createUseCase.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}