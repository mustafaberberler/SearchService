package com.ege.microservices.search.search_service.controllers.impls;

import com.ege.microservices.search.search_service.controllers.CategoryController;
import com.ege.microservices.search.search_service.controllers.responses.CategoryModel;
import com.ege.microservices.search.search_service.convert.ModelConverters.CategoryModelConverter;
import com.ege.microservices.search.search_service.services.CategoryService;
import com.ege.microservices.search.search_service.services.dtos.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryControllerImpl implements CategoryController {

    private final CategoryService categoryService;

    private final CategoryModelConverter categoryModelConverter;

    @Override
    public ResponseEntity<CategoryModel> getCategoryByName(String categoryName) {

        CategoryDto categoryDto = categoryService.getCategoryByName(categoryName);

        CategoryModel categoryModel = categoryModelConverter.convertCategoryDtoToCategoryModel(categoryDto);

        return new ResponseEntity<>(categoryModel, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<List<CategoryModel>> getAllCategories() {

        List<CategoryDto> categoryDtoList = categoryService.getAllCategories();

        List<CategoryModel> categoryModels = categoryModelConverter.convertCategoryDtoListToCategoryModelList(categoryDtoList);

        return new ResponseEntity<>(categoryModels, HttpStatus.OK);

    }
}
