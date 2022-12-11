package io.github.skosijer.lotr;

import io.github.skosijer.lotr.api.response.BookPageResponse;
import io.github.skosijer.lotr.util.JsonObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class LotrClient {

    public CompletableFuture<BookPageResponse> getBooks(String bookId) {
        var client = HttpClient.newHttpClient();
        var uriTemplate = String.format("https://the-one-api.dev/v2/book/%s", bookId);
        var request = HttpRequest.newBuilder()
            .uri(URI.create(uriTemplate))
            .GET()
            .build();

        var objectMapper = new JsonObjectMapper();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenApply(response -> objectMapper.readValue(response, BookPageResponse.class));
    }
}