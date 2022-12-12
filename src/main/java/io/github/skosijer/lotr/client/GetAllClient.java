package io.github.skosijer.lotr.client;

import static io.github.skosijer.lotr.client.HttpClientWithToken.createRequest;
import static io.github.skosijer.lotr.util.ApiConstants.LOTR_API_URL;
import static io.github.skosijer.lotr.util.ApiConstants.QUERY_PARAMETERS_SEPARATOR;
import static io.github.skosijer.lotr.util.ResponseObjectMapper.objectMapper;

import io.github.skosijer.lotr.api.request.Query;
import io.github.skosijer.lotr.util.QueryUtil;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;

/**
 * Generic client for serving any requests in the following form: e.g.    /book e.g.    /character
 *
 * @param <T> generic response type of the API
 */
@RequiredArgsConstructor
public class GetAllClient<T> {

    private static final String GET_ALL_URI_FORMAT = LOTR_API_URL + "/%s";

    private final Class<T> resourceClass;

    private final String resourceName;

    public CompletableFuture<T> getAll(Query query) {
        var uriBuilder = new StringBuilder();
        uriBuilder.append(String.format(GET_ALL_URI_FORMAT, resourceName));
        if (query != null) {
            uriBuilder.append(QUERY_PARAMETERS_SEPARATOR);
            uriBuilder.append(QueryUtil.buildQueryParams(query));
        }

        var request = createRequest(uriBuilder.toString());

        return HttpClientWithToken.httpClient
            .sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenApply(response -> objectMapper().readContent(response, resourceClass));
    }
}
