package io.github.skosijer.lotr.service;

import io.github.skosijer.lotr.api.request.Query;
import io.github.skosijer.lotr.api.response.BookPage;
import io.github.skosijer.lotr.api.response.ChapterPage;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BookService {

    private static final String BOOK_RESOURCE = "book";

    private static final String CHAPTER_RESOURCE = "chapter";

    private final GetByIdClient<BookPage> getByIdClient = new GetByIdClient<>(BookPage.class, BOOK_RESOURCE);

    private final GetAllClient<BookPage> getAllClient = new GetAllClient<>(BookPage.class, BOOK_RESOURCE);

    private final GetAllByIdClient<ChapterPage> getAllByIdClient = new GetAllByIdClient<>(ChapterPage.class,
        BOOK_RESOURCE,
        CHAPTER_RESOURCE);

    public CompletableFuture<BookPage> getBook(String id) {
        return getByIdClient.getById(id);
    }

    public CompletableFuture<BookPage> getBooks(Query query) {
        return getAllClient.getAll(query);
    }

    public CompletableFuture<ChapterPage> getBookChapters(String id, Query query) {
        return getAllByIdClient.getAllById(id, query);
    }
}
