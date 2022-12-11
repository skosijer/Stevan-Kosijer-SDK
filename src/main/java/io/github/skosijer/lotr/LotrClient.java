package io.github.skosijer.lotr;

import io.github.skosijer.lotr.api.request.Query;
import io.github.skosijer.lotr.api.response.BookPage;
import io.github.skosijer.lotr.api.response.ChapterPage;
import io.github.skosijer.lotr.client.AuthHttpClient;
import io.github.skosijer.lotr.exception.EntityIdNullException;
import io.github.skosijer.lotr.service.impl.BookService;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import lombok.NonNull;

/**
 * Use this Lotr API implementation to execute all the endpoints and variations that Lotr API offers.
 */
public class LotrClient implements ILotrClient {

    public LotrClient(String bearerToken) {
        AuthHttpClient.initialize(bearerToken);
    }

    private final BookService bookService = new BookService();

    public CompletableFuture<BookPage> books() {
        return bookService.getBooks(null);
    }

    public CompletableFuture<BookPage> books(Query query) {
        return bookService.getBooks(query);
    }

    public CompletableFuture<BookPage> book(@NonNull String bookId) {
        if (Objects.isNull(bookId)) {
            return CompletableFuture.failedFuture(new EntityIdNullException());
        }

        return bookService.getBook(bookId);
    }

    public CompletableFuture<ChapterPage> bookChapters(@NonNull String bookId, Query query) {
        if (Objects.isNull(bookId)) {
            return CompletableFuture.failedFuture(new EntityIdNullException());
        }

        return bookService.getBookChapters(bookId, query);
    }
}