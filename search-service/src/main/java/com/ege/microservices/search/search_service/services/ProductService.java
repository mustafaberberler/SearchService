package com.ege.microservices.search.search_service.services;

import com.ege.microservices.search.search_service.services.dtos.ProductDto;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    // Find product by ID
    ProductDto getProductById(String productId);

    // Find products by description
    List<ProductDto> getProductsByDescription(String description);

    // Find products by name
    List<ProductDto> getProductsByName(String productName);

    // Find products by price interval
    List<ProductDto> getProductsByPrice(BigDecimal minPrice, BigDecimal maxPrice);

    // Find products by category name
    List<ProductDto> getProductsByCategoryName(String categoryName);

    // Find all products
    List<ProductDto> getAllProducts();


}
