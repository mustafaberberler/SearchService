package com.ege.microservices.search.search_service.controllers;

import com.ege.microservices.search.search_service.constants.ProductRestPath;
import com.ege.microservices.search.search_service.controllers.responses.ProductModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@RequestMapping(value = ProductRestPath.PRODUCT_PATH)
public interface ProductController {

    @GetMapping(value = ProductRestPath.GET_BY_ID_PATH + "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProductModel> getProductById(@PathVariable String productId);

    @GetMapping(value = ProductRestPath.GET_BY_DESCRIPTION_PATH + "/{description}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ProductModel>> getProductsByDescription(@PathVariable String description);

    @GetMapping(value = ProductRestPath.GET_BY_NAME_PATH + "/{productName}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ProductModel>> getProductsByName(@PathVariable String productName);

    @GetMapping(value = ProductRestPath.GET_BY_PRICE_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ProductModel>> getProductsByPrice(@RequestParam BigDecimal minPrice, @RequestParam BigDecimal maxPrice);

    @GetMapping(value = ProductRestPath.GET_BY_CATEGORY_NAME + "/{categoryName}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ProductModel>> getProductByCategoryName(@PathVariable String categoryName);

    @GetMapping(value = ProductRestPath.GET_ALL_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ProductModel>> getAllProducts();

}
