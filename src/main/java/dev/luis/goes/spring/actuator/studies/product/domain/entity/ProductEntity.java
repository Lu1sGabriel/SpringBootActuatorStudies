package dev.luis.goes.spring.actuator.studies.product.domain.entity;

import dev.luis.goes.spring.actuator.studies.rating.domain.entity.RatingEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RatingEntity> ratings = new ArrayList<>();

    public ProductEntity(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    public void addRating(RatingEntity rating) {
        ratings.add(rating);
        rating.setProduct(this);
    }

    public void removeRating(RatingEntity rating) {
        ratings.remove(rating);
        rating.setProduct(null);
    }

    public void update() {
        this.updatedAt = Instant.now();
    }

}