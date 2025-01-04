package com.ege.microservices.search.search_service.convert.ModelConverters;


import com.ege.microservices.search.search_service.controllers.requests.ProductRequestModel;
import com.ege.microservices.search.search_service.controllers.responses.ProductModel;
import com.ege.microservices.search.search_service.services.dtos.ProductDto;
import com.ege.microservices.search.search_service.services.dtos.ProductRequestDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductModelConverter {

    private final ModelMapper modelMapper;

    public ProductRequestDto convertProductRequestModelToProductRequestDto(ProductRequestModel productRequestModel){

        ProductRequestDto productRequestDto = modelMapper.map(productRequestModel, ProductRequestDto.class);

        return productRequestDto;
    }

    public ProductModel convertProductDtoToProductModel(ProductDto productDto){

        ProductModel productModel = modelMapper.map(productDto, ProductModel.class);

        return productModel;
    }

    public List<ProductModel>  convertProductDtoListToProductModelList(List<ProductDto> productDtoList){

        List<ProductModel> productModelList = modelMapper.map(productDtoList, new TypeToken<List<ProductModel>>() {}.getType());

        return productModelList;
    }
}
