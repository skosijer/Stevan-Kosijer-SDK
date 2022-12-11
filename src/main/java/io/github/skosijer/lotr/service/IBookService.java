package io.github.skosijer.lotr.service;

import io.github.skosijer.lotr.api.request.Query;
import io.github.skosijer.lotr.api.response.BookPage;
import io.github.skosijer.lotr.api.response.ChapterPage;
import java.util.concurrent.CompletableFuture;

public interface IBookService {

    CompletableFuture<BookPage> getBook(String id);

    CompletableFuture<BookPage> getBooks(Query query);

    CompletableFuture<ChapterPage> getBookChapters(String id, Query query);
}
