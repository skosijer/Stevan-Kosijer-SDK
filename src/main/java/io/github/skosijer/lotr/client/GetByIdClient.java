package io.github.skosijer.lotr.client;

import static io.github.skosijer.lotr.util.ApiConstants.LOTR_API_URL;
import static io.github.skosijer.lotr.util.ResponseObjectMapper.objectMapper;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;

/**
 * Generic client for serving any requests in the following form: e.g.     /book/{id}/chapter e.g.
 * /character/{id}/quote
 *
 * @param <T> generic response type of the API
 */
@RequiredArgsConstructor
public class GetByIdClient<T> {

    private static final String GET_BY_ID_URI_FORMAT = LOTR_API_URL + "/%s/%s";

    private final Class<T> resourceClass;

    private final String resourceName;

    public CompletableFuture<T> getById(String id) {
        var uriTemplate = String.format(GET_BY_ID_URI_FORMAT, resourceName, id);

        var request = createRequest(uriTemplate);

        return AuthHttpClient.getInstance()
            .sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenApply(response -> objectMapper().readContent(response, resourceClass));
    }

    private HttpRequest createRequest(String uri) {
        return HttpRequest.newBuilder()
            .uri(URI.create(uri))
            .GET()
            .build();
    }
}
