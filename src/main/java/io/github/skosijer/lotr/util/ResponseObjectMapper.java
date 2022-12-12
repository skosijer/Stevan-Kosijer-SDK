package io.github.skosijer.lotr.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.concurrent.CompletionException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseObjectMapper {

    public static ObjectMapper instance;

    public static ObjectMapper getInstance() {
        if (instance == null) {
            instance = new ObjectMapper();
        }
        return instance;
    }

    public static <T> T readResponse(String response, Class<T> responseClass) {
        try {
            return getInstance().readValue(response, responseClass);
        } catch (IOException ioe) {
            throw new CompletionException(ioe);
        }
    }
}
