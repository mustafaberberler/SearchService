package com.ege.microservices.search.search_service.repositories;


import com.ege.microservices.search.search_service.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {

    CategoryEntity findCategoryEntityByCategoryName(String categoryName);

}
