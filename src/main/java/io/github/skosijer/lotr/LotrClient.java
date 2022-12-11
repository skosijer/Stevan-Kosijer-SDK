package io.github.skosijer.lotr;

import io.github.skosijer.lotr.api.response.BookPageResponse;
import io.github.skosijer.lotr.service.AuthHttpClient;
import io.github.skosijer.lotr.service.BookClient;
import java.util.concurrent.CompletableFuture;

public class LotrClient {

    private final BookClient bookClient = new BookClient();

    public LotrClient(String bearerToken) {
        AuthHttpClient.initialize(bearerToken);
    }

    public CompletableFuture<BookPageResponse> books() {
        return bookClient.getBooks();
    }

    public CompletableFuture<BookPageResponse> book(String id) {
        return bookClient.getBook(id);
    }
}