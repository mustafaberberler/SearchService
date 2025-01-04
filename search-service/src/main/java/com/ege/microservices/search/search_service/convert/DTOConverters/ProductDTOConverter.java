package com.ege.microservices.search.search_service.convert.DTOConverters;


import com.ege.microservices.search.search_service.entities.ProductEntity;
import com.ege.microservices.search.search_service.services.dtos.ProductDto;
import com.ege.microservices.search.search_service.services.dtos.ProductRequestDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductDTOConverter {

    private final ModelMapper modelMapper;

    public ProductEntity convertProductRequestDtoToProductEntity(ProductRequestDto productRequestDto){

        ProductEntity productEntity = modelMapper.map(productRequestDto, ProductEntity.class);

        return productEntity;
    }

    public ProductDto convertProductEntityToProductDto(ProductEntity productEntity){

        ProductDto productDto = modelMapper.map(productEntity, ProductDto.class);

        return productDto;
    }

    public List<ProductDto> convertProductEntityListToProductDtoList(List<ProductEntity> productEntityList){

        List<ProductDto> productDtoList = modelMapper.map(productEntityList, new TypeToken<List<ProductDto>>() {}.getType());

        return productDtoList;
    }
}
