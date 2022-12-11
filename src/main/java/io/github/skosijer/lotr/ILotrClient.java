package io.github.skosijer.lotr;

import io.github.skosijer.lotr.api.request.Query;
import io.github.skosijer.lotr.api.response.BookPage;
import io.github.skosijer.lotr.api.response.ChapterPage;
import java.util.concurrent.CompletableFuture;
import lombok.NonNull;

/**
 * Use this Lotr API to execute all the endpoints and variations that Lotr API offers.
 */
public interface ILotrClient {

    CompletableFuture<BookPage> books();

    CompletableFuture<BookPage> books(Query query);

    CompletableFuture<BookPage> book(@NonNull String bookId);

    CompletableFuture<ChapterPage> bookChapters(@NonNull String bookId, Query query);
}
