package com.ege.microservices.search.search_service.controllers;

import com.ege.microservices.search.search_service.constants.CategoryRestPath;
import com.ege.microservices.search.search_service.controllers.responses.CategoryModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(value = CategoryRestPath.CATEGORY_PATH)
public interface CategoryController {

    @GetMapping(value = CategoryRestPath.GET_BY_NAME_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CategoryModel> getCategoryByName(String categoryName);

    @GetMapping(value = CategoryRestPath.GET_ALL_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<CategoryModel>> getAllCategories();

}
