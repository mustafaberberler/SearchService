package com.ege.microservices.search.search_service.services.impl;

import com.ege.microservices.search.search_service.convert.DTOConverters.ProductDTOConverter;
import com.ege.microservices.search.search_service.entities.ProductEntity;
import com.ege.microservices.search.search_service.repositories.ProductRepository;
import com.ege.microservices.search.search_service.services.LogService;
import com.ege.microservices.search.search_service.services.ProductService;
import com.ege.microservices.search.search_service.services.dtos.ProductDto;
import com.ege.microservices.search.search_service.utils.rabbitutils.RabbitMQClient;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    private final LogService logService;

    /// 03.02.2025
    private final ObjectMapper objectMapper;
    private static final String LOG_QUEUE = "log_service_queue";
    private final RabbitMQClient rabbitMQClient;

    @Override
    public ProductDto getProductById(String productId) {

        log.info("Getting the product by ID.");

        ProductEntity productEntity = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + productId));

        //logService.sendLog("INFO", "Getting the product with ID: " + productId, "Search Service");

        /// 03.02.2025
        logToService("INFO", "Getting the product with ID: " + productId);

        return productDTOConverter.convertProductEntityToProductDto(productEntity);

    }


    @Override
    public List<ProductDto> getProductsByDescription(String description) {

        log.info("Getting products by description.");

        List<ProductEntity> productEntityList = productRepository.findByDescriptionContainingIgnoreCase(description);

       // logService.sendLog("INFO", "Getting the products with description: " + description, "Search Service");

        /// 03.02.2025
        logToService("INFO", "Getting the products with description: " + description);

        return productDTOConverter.convertProductEntityListToProductDtoList(productEntityList);

    }

    @Override
    public List<ProductDto> getProductsByName(String productName) {

        log.info("Getting products by product name.");

        List<ProductEntity> productEntityList = productRepository.findByProductNameContainingIgnoreCase(productName);

       // logService.sendLog("INFO", "Getting the products by name: " + productName, "Search Service");

        /// 03.02.2025
        logToService("INFO", "Getting the products by name: " + productName);

        return productDTOConverter.convertProductEntityListToProductDtoList(productEntityList);

    }

    @Override
    public List<ProductDto> getProductsByPrice(BigDecimal minPrice, BigDecimal maxPrice) {

        log.info("Getting products between the minimum price and maximum price.");

        List<ProductEntity> productEntityList = productRepository.findByPriceBetween(minPrice, maxPrice);

       // logService.sendLog("INFO", "Getting the products by price interval: " + minPrice + " and " + maxPrice, "Search Service");

        /// 03.02.2025
        logToService("INFO", "Getting the products by price interval: " + minPrice + " and " + maxPrice);

        return productDTOConverter.convertProductEntityListToProductDtoList(productEntityList);

    }

    @Override
    public List<ProductDto> getProductsByCategoryName(String categoryName) {

        log.info("Getting products by category.");

        List<ProductEntity> productEntityList = productRepository.findByCategory_CategoryName(categoryName);

        //logService.sendLog("INFO", "Getting the products by category name: " + categoryName, "Search Service");

        /// 03.02.2025
        logToService("INFO", "Getting the products by category name: " + categoryName);

        return productDTOConverter.convertProductEntityListToProductDtoList(productEntityList);

    }

    @Override
    public List<ProductDto> getAllProducts() {

        log.info("Getting all products.");

        List<ProductEntity> productEntityList = productRepository.findAll();

       // logService.sendLog("INFO", "Getting all products: ","Search Service");

        /// 03.02.2025
        logToService("INFO", "Getting all products: ");

        return productDTOConverter.convertProductEntityListToProductDtoList(productEntityList);

    }

    ///  03.02.2025
    private void logToService(String level, String message) {
        try {
            String logPayload = objectMapper.writeValueAsString(new LogRequest("search-service", level, message));
            rabbitMQClient.sendAndReceive(LOG_QUEUE, logPayload);
        } catch (Exception e) {
            System.err.println("Failed to send log to RabbitMQ: " + message);
        }
    }

    /// 03.02.2025
    private static class LogRequest {
        private String service;
        private Content content;

        public LogRequest(String service, String level, String message) {
            this.service = service;
            this.content = new Content(level, message);
        }

        private static class Content {
            private String level;
            private String message;

            public Content(String level, String message) {
                this.level = level;
                this.message = message;
            }
        }
    }

}
