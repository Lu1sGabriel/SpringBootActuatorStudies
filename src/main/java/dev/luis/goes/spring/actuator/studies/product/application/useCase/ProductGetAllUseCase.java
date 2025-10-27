package dev.luis.goes.spring.actuator.studies.product.application.useCase;

import dev.luis.goes.spring.actuator.studies.product.domain.entity.ProductEntity;
import dev.luis.goes.spring.actuator.studies.product.infrastructure.repository.ProductRepository;
import dev.luis.goes.spring.actuator.studies.product.presentation.dtos.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductGetAllUseCase {

    private final ProductRepository productRepository;

    public Page<ProductResponse> get(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("name").ascending());
        Page<ProductEntity> products = productRepository.findAll(pageable);

        return products.map(product -> new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                null,
                null
        ));
    }

}