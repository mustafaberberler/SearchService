package com.ege.microservices.search.search_service.services.impl;

import com.ege.microservices.search.search_service.convert.DTOConverters.CategoryDTOConverter;
import com.ege.microservices.search.search_service.entities.CategoryEntity;
import com.ege.microservices.search.search_service.repositories.CategoryRepository;
import com.ege.microservices.search.search_service.services.CategoryService;
import com.ege.microservices.search.search_service.services.LogService;
import com.ege.microservices.search.search_service.services.dtos.CategoryDto;
import com.ege.microservices.search.search_service.utils.rabbitutils.RabbitMQClient;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    private final LogService logService;


    /// 03.02.2025
    private final ObjectMapper objectMapper;
    private static final String LOG_QUEUE = "log_service_queue";
    private final RabbitMQClient rabbitMQClient;

    @Override
    public CategoryDto getCategoryByName(String categoryName) {

        log.info("Getting category by name.");

        CategoryEntity categoryEntity = categoryRepository.findCategoryEntityByCategoryName(categoryName);

        //logService.sendLog("INFO", "Getting the category by name: " + categoryName, "Search Service");

        /// 03.02.2025
        logToService("INFO", "Getting the category by name: " + categoryName);

        return categoryDTOConverter.convertCategoryEntityToCategoryDto(categoryEntity);

    }

    @Override
    public List<CategoryDto> getAllCategories() {

        log.info("Getting all categories.");

        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();

        //logService.sendLog("INFO", "Getting all categories: ", "Search Service");

        /// 03.02.2025
        logToService("INFO", "Getting all categories: ");

        return categoryDTOConverter.convertCategoryEntityListToCategoryDtoList(categoryEntityList);

    }

    ///  03.02.2025
    private void logToService(String level, String message) {
        try {
            String logPayload = objectMapper.writeValueAsString(new LogRequest("product-service", level, message));
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
