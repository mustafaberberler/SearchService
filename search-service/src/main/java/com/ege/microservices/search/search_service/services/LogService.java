package com.ege.microservices.search.search_service.services;

public interface LogService {

    void sendLog(String level, String message, String serviceName);

}
