package com.eventuate.example.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;


@Slf4j
public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private JsonUtils() {
        throw new IllegalStateException("Utility class.");
    }

    public static String objectToString(Object source) {
        try {
            return objectMapper.writeValueAsString(source);
        } catch (JsonProcessingException e) {
            log.error("objectToString error => ", e);
            return null;
        }
    }

    public static <T> T jsonToObject(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            log.error("jsonToObject error => ", e);
            return null;
        }
    }

    public static <T> T jsonToObject(String json, TypeReference<T> type) {
        try {
            return objectMapper.readValue(json,type);
        } catch (IOException e) {
            log.error("jsonToObject error => ", e);
            return null;
        }
    }

}
