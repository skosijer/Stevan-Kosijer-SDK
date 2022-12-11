package io.github.skosijer.lotr;

import io.github.skosijer.lotr.api.request.Query;
import io.github.skosijer.lotr.api.response.BookPage;
import io.github.skosijer.lotr.api.response.ChapterPage;
import io.github.skosijer.lotr.service.AuthHttpClient;
import io.github.skosijer.lotr.service.BookService;
import java.util.concurrent.CompletableFuture;

public class LotrClient {

    private final BookService bookService = new BookService();

    public LotrClient(String bearerToken) {
        AuthHttpClient.initialize(bearerToken);
    }

    public CompletableFuture<BookPage> books() {
        return bookService.getBooks(null);
    }

    public CompletableFuture<BookPage> books(Query query) {
        return bookService.getBooks(query);
    }

    public CompletableFuture<BookPage> book(String bookId) {
        return bookService.getBook(bookId);
    }

    public CompletableFuture<ChapterPage> bookChapters(String bookId, Query query) {
        return bookService.getBookChapters(bookId, query);
    }
}