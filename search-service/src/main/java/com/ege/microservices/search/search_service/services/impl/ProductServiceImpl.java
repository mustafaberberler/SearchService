package com.ege.microservices.search.search_service.services.impl;

import com.ege.microservices.search.search_service.convert.DTOConverters.ProductDTOConverter;
import com.ege.microservices.search.search_service.entities.ProductEntity;
import com.ege.microservices.search.search_service.repositories.ProductRepository;
import com.ege.microservices.search.search_service.services.ProductService;
import com.ege.microservices.search.search_service.services.dtos.ProductDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductDTOConverter productDTOConverter;

    @Override
    public ProductDto getProductById(String productId) {

        log.info("Getting the product by ID.");

        ProductEntity productEntity = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + productId));

        return productDTOConverter.convertProductEntityToProductDto(productEntity);

    }


    @Override
    public List<ProductDto> getProductsByDescription(String description) {

        log.info("Getting products by description.");

        List<ProductEntity> productEntityList = productRepository.findByDescriptionContainingIgnoreCase(description);

        return productDTOConverter.convertProductEntityListToProductDtoList(productEntityList);

    }

    @Override
    public List<ProductDto> getProductsByName(String productName) {

        log.info("Getting products by product name.");

        List<ProductEntity> productEntityList = productRepository.findByProductNameContainingIgnoreCase(productName);

        return productDTOConverter.convertProductEntityListToProductDtoList(productEntityList);

    }

    @Override
    public List<ProductDto> getProductsByPrice(BigDecimal minPrice, BigDecimal maxPrice) {

        log.info("Getting products between the minimum price and maximum price.");

        List<ProductEntity> productEntityList = productRepository.findByPriceBetween(minPrice, maxPrice);

        return productDTOConverter.convertProductEntityListToProductDtoList(productEntityList);

    }

    @Override
    public List<ProductDto> getProductsByCategoryName(String categoryName) {

        log.info("Getting products by category.");

        List<ProductEntity> productEntityList = productRepository.findByCategory_CategoryName(categoryName);

        return productDTOConverter.convertProductEntityListToProductDtoList(productEntityList);

    }

    @Override
    public List<ProductDto> getAllProducts() {

        log.info("Getting all products.");

        List<ProductEntity> productEntityList = productRepository.findAll();

        return productDTOConverter.convertProductEntityListToProductDtoList(productEntityList);

    }

}
