package io.github.skosijer.lotr.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.concurrent.CompletionException;

public class JsonObjectMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public <T> T readValue(String content, Class<T> contentClass) {
        try {
            return objectMapper.readValue(content, contentClass);
        } catch (IOException ioe) {
            throw new CompletionException(ioe);
        }
    }
}
