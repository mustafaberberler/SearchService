package com.ege.microservices.search.search_service.repositories;

import com.ege.microservices.search.search_service.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {

    List<ProductEntity> findByProductNameContainingIgnoreCase(String productName);

    List<ProductEntity> findByDescriptionContainingIgnoreCase(String description);

    List<ProductEntity> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    List<ProductEntity> findByCategory_CategoryName(String category);

}
