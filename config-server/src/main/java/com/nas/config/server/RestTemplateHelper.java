package com.nas.config.server;


import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class RestTemplateHelper {

    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;


    @Autowired
    public RestTemplateHelper(RestTemplateBuilder restTemplateBuilder, ObjectMapper objectMapper){
        this.restTemplate = restTemplateBuilder.build();
        this.objectMapper = objectMapper;
    }

    // find by id
    public <T> T getForEntity(Class<T> eClass, String url, Object... uriVariables){
        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class, uriVariables);
            JavaType javaType = objectMapper.getTypeFactory().constructType(eClass);
            return readValue(responseEntity, javaType);
        }catch (HttpClientErrorException exception){
            if(exception.getStatusCode() == HttpStatus.NOT_FOUND)
                log.info("[+] No data found {}", url);
            else
                log.info("[+] Rest Client exception {}", exception.getMessage());
        }
        return null;
    }

    // find all
    public <T> List<T> getForList(Class<T> clazz, String url, Object... uriVariables) {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class, uriVariables);
            CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, clazz);
            return readValue(response, collectionType);
        } catch (HttpClientErrorException exception) {
            if (exception.getStatusCode() == HttpStatus.NOT_FOUND) {
                log.info("No data found {}", url);
            } else {
                log.info("rest client exception", exception.getMessage());
            }
        }
        return null;
    }

    // Save
    public <T, R> T postForEntity(Class<T> clazz, String url, R body, Object... uriVariables) {
        HttpEntity<R> request = new HttpEntity<>(body);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class, uriVariables);
        JavaType javaType = objectMapper.getTypeFactory().constructType(clazz);
        return readValue(response, javaType);
    }

    // Update
    public <T, R> T putForEntity(Class<T> clazz, String url, R body, Object... uriVariables) {
        HttpEntity<R> request = new HttpEntity<>(body);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, request, String.class, uriVariables);
        JavaType javaType = objectMapper.getTypeFactory().constructType(clazz);
        return readValue(response, javaType);
    }

    // Delete
    public void delete(String url, Object... uriVariables) {
        try {
            restTemplate.delete(url, uriVariables);
        } catch (RestClientException exception) {
            log.info(exception.getMessage());
        }
    }


    private <T> T readValue(ResponseEntity<String> responseEntity, JavaType javaType){
        T result = null;
        if (responseEntity.getStatusCode() == HttpStatus.OK ||
                responseEntity.getStatusCode() == HttpStatus.CREATED) {
            try {
                result = objectMapper.readValue(responseEntity.getBody(), javaType);
            } catch (IOException e) {
                log.info(e.getMessage());
            }
        } else {
            log.info("No data found {}", responseEntity.getStatusCode());
        }
        return result;
    }
}
