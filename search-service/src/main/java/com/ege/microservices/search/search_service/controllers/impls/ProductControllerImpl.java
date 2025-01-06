package com.ege.microservices.search.search_service.controllers.impls;

import com.ege.microservices.search.search_service.controllers.ProductController;
import com.ege.microservices.search.search_service.controllers.responses.ProductModel;
import com.ege.microservices.search.search_service.convert.ModelConverters.ProductModelConverter;
import com.ege.microservices.search.search_service.services.ProductService;
import com.ege.microservices.search.search_service.services.dtos.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductControllerImpl implements ProductController {

    private final ProductService productService;

    private final ProductModelConverter productModelConverter;

    @Override
    public ResponseEntity<ProductModel> getProductById(String productId) {

        ProductDto productDto = productService.getProductById(productId);

        ProductModel productModel = productModelConverter.convertProductDtoToProductModel(productDto);

        return new ResponseEntity<>(productModel, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<List<ProductModel>> getProductsByDescription(String description) {

        List<ProductDto> productDtoList = productService.getProductsByDescription(description);

        List<ProductModel> productModelList = productModelConverter.convertProductDtoListToProductModelList(productDtoList);

        return new ResponseEntity<>(productModelList, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<List<ProductModel>> getProductsByName(String productName) {

        List<ProductDto> productDtoList = productService.getProductsByName(productName);

        List<ProductModel> productModelList = productModelConverter.convertProductDtoListToProductModelList(productDtoList);

        return new ResponseEntity<>(productModelList, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<List<ProductModel>> getProductsByPrice(BigDecimal minPrice, BigDecimal maxPrice) {

        List<ProductDto> productDtoList = productService.getProductsByPrice(minPrice, maxPrice);

        List<ProductModel> productModelList = productModelConverter.convertProductDtoListToProductModelList(productDtoList);

        return new ResponseEntity<>(productModelList, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<List<ProductModel>> getProductByCategoryName(String categoryName) {

        List<ProductDto> productDtoList = productService.getProductsByCategoryName(categoryName);

        List<ProductModel> productModelList = productModelConverter.convertProductDtoListToProductModelList(productDtoList);

        return new ResponseEntity<>(productModelList, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<List<ProductModel>> getAllProducts() {

        List<ProductDto> productDtoList = productService.getAllProducts();

        List<ProductModel> productModelList = productModelConverter.convertProductDtoListToProductModelList(productDtoList);

        return new ResponseEntity<>(productModelList, HttpStatus.OK);

    }
}
