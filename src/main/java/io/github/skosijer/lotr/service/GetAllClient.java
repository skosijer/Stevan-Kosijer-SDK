package io.github.skosijer.lotr.service;

import static io.github.skosijer.lotr.util.ApiConstants.LOTR_API_URL;
import static io.github.skosijer.lotr.util.ResponseObjectMapper.objectMapper;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetAllClient<T> {

    public static final String GET_ALL_URI_FORMAT = LOTR_API_URL + "/%s";

    private final Class<T> resourceClass;

    private final String resourceName;

    public CompletableFuture<T> getAll() {
        var uriTemplate = String.format(GET_ALL_URI_FORMAT, resourceName);
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
