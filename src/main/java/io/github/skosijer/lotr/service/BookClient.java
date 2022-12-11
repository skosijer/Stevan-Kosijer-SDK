package io.github.skosijer.lotr.service;

import io.github.skosijer.lotr.api.response.BookPageResponse;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BookClient {

    private static final String RESOURCE = "book";

    private final GetByIdClient<BookPageResponse> getByIdClient = new GetByIdClient<>(BookPageResponse.class, RESOURCE);

    private final GetAllClient<BookPageResponse> getAllClient = new GetAllClient<>(BookPageResponse.class, RESOURCE);

    public CompletableFuture<BookPageResponse> getBook(String id) {
        return getByIdClient.getById(id);
    }

    public CompletableFuture<BookPageResponse> getBooks() {
        return getAllClient.getAll();
    }
}
