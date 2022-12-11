package io.github.skosijer.lotr.client;

import static io.github.skosijer.lotr.util.ApiConstants.LOTR_API_URL;
import static io.github.skosijer.lotr.util.ApiConstants.QUERY_PARAMETERS_SEPARATOR;
import static io.github.skosijer.lotr.util.ResponseObjectMapper.objectMapper;

import io.github.skosijer.lotr.api.request.Query;
import io.github.skosijer.lotr.util.QueryUtil;
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
public class GetAllByIdClient<T> {

    private static final String GET_ALL_BY_ID_URI_FORMAT = LOTR_API_URL + "/%s/%s/%s";

    private final Class<T> resourceClass;

    private final String parentResource;

    private final String childResource;

    public CompletableFuture<T> getAllById(String id, Query query) {
        var uriBuilder = new StringBuilder();
        uriBuilder.append(String.format(GET_ALL_BY_ID_URI_FORMAT, parentResource, id, childResource));
        if (query != null) {
            uriBuilder.append(QUERY_PARAMETERS_SEPARATOR);
            uriBuilder.append(QueryUtil.buildQueryParams(query));
        }

        var request = HttpRequest.newBuilder()
            .uri(URI.create(uriBuilder.toString()))
            .GET()
            .build();

        return AuthHttpClient.getInstance()
            .sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenApply(response -> objectMapper().readContent(response, resourceClass));
    }
}
