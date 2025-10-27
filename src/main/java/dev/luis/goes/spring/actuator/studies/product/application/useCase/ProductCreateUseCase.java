package dev.luis.goes.spring.actuator.studies.product.application.useCase;

import dev.luis.goes.spring.actuator.studies.product.domain.entity.ProductEntity;
import dev.luis.goes.spring.actuator.studies.product.infrastructure.repository.ProductRepository;
import dev.luis.goes.spring.actuator.studies.product.presentation.dtos.request.ProductRequest;
import dev.luis.goes.spring.actuator.studies.product.presentation.dtos.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductCreateUseCase {

    private final ProductRepository productRepository;

    public ProductResponse crate(ProductRequest request) {
        ProductEntity productEntity = productRepository.save(new ProductEntity(request.name(), request.price()));
        return new ProductResponse(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getPrice()
        );
    }

}