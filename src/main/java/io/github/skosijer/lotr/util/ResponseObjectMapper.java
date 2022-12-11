package io.github.skosijer.lotr.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.concurrent.CompletionException;

public class ResponseObjectMapper extends ObjectMapper {

    // It is not advised for multithreaded runtime like java to reuse singleton ObjectMapper
    // Possible improvement of this solution would be to use an ObjectMapper fixed pool
    public static ResponseObjectMapper objectMapper() {
        return new ResponseObjectMapper();
    }

    public <T> T readContent(String content, Class<T> contentClass) {
        try {
            return this.readValue(content, contentClass);
        } catch (IOException ioe) {
            throw new CompletionException(ioe);
        }
    }
}
