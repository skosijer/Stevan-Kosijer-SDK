package io.github.skosijer.lotr.client;

import static io.github.skosijer.lotr.client.HttpClientWithToken.createRequest;
import static io.github.skosijer.lotr.util.ApiConstants.LOTR_API_URL;
import static io.github.skosijer.lotr.util.ResponseObjectMapper.readResponse;

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

        return HttpClientWithToken.httpClient
            .sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenApply(response -> readResponse(response, resourceClass));
    }
}
