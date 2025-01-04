package com.ege.microservices.search.search_service.services.impl;

import com.ege.microservices.search.search_service.convert.DTOConverters.CategoryDTOConverter;
import com.ege.microservices.search.search_service.entities.CategoryEntity;
import com.ege.microservices.search.search_service.repositories.CategoryRepository;
import com.ege.microservices.search.search_service.services.CategoryService;
import com.ege.microservices.search.search_service.services.dtos.CategoryDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryDTOConverter categoryDTOConverter;

    @Override
    public CategoryDto getCategoryByName(String categoryName) {

        log.info("Getting category by name.");

        CategoryEntity categoryEntity = categoryRepository.findCategoryEntityByCategoryName(categoryName);

        return categoryDTOConverter.convertCategoryEntityToCategoryDto(categoryEntity);

    }

    @Override
    public List<CategoryDto> getAllCategories() {

        log.info("Getting all categories.");

        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();

        return categoryDTOConverter.convertCategoryEntityListToCategoryDtoList(categoryEntityList);

    }
}
