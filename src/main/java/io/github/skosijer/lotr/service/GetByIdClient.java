package io.github.skosijer.lotr.service;

import static io.github.skosijer.lotr.util.ApiConstants.LOTR_API_URL;
import static io.github.skosijer.lotr.util.ResponseObjectMapper.objectMapper;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetByIdClient<T> {

    public static final String GET_BY_ID_URI_FORMAT = LOTR_API_URL + "/%s/%s";

    private final Class<T> resourceClass;

    private final String resourceName;

    public CompletableFuture<T> getById(String id) {
        var uriTemplate = String.format(GET_BY_ID_URI_FORMAT, resourceName, id);
        var request = HttpRequest.newBuilder()
            .uri(URI.create(uriTemplate))
            .GET()
            .build();

        return AuthHttpClient.getInstance()
            .sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenApply(response -> objectMapper().readContent(response, resourceClass));
    }
}
