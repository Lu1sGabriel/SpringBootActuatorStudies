package dev.luis.goes.spring.actuator.studies.product.presentation.controller;

import dev.luis.goes.spring.actuator.studies.product.application.useCase.ProductCreateUseCase;
import dev.luis.goes.spring.actuator.studies.product.application.useCase.ProductGetAllUseCase;
import dev.luis.goes.spring.actuator.studies.product.application.useCase.ProductGetByIdUseCase;
import dev.luis.goes.spring.actuator.studies.product.presentation.dtos.request.ProductRequest;
import dev.luis.goes.spring.actuator.studies.product.presentation.dtos.response.ProductResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductGetByIdUseCase getByIdUseCase;
    private final ProductGetAllUseCase getAllUseCase;
    private final ProductCreateUseCase productCreateUseCase;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable(value = "id") UUID id) {
        ProductResponse response = getByIdUseCase.get(id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getAll(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                                        @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        Page<ProductResponse> responsePage = getAllUseCase.get(page, pageSize);
        return ResponseEntity.ok().body(responsePage);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody @Valid ProductRequest request) {
        ProductResponse response = productCreateUseCase.crate(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}