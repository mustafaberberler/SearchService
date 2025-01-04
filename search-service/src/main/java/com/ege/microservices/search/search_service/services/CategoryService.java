package com.ege.microservices.search.search_service.services;

import com.ege.microservices.search.search_service.services.dtos.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto getCategoryByName(String categoryName);

    List<CategoryDto> getAllCategories();

}
