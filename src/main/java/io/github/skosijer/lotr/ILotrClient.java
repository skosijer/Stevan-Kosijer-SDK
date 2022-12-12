package io.github.skosijer.lotr;

import io.github.skosijer.lotr.api.request.Query;
import io.github.skosijer.lotr.api.response.BookPage;
import io.github.skosijer.lotr.api.response.ChapterPage;
import io.github.skosijer.lotr.api.response.CharacterPage;
import io.github.skosijer.lotr.api.response.MoviePage;
import io.github.skosijer.lotr.api.response.QuotePage;
import java.util.concurrent.CompletableFuture;

/**
 * Use this Lotr API to execute all the endpoints and variations that Lotr API offers.
 */
public interface ILotrClient {

    CompletableFuture<BookPage> books();

    CompletableFuture<BookPage> books(Query query);

    CompletableFuture<BookPage> book(String bookId);

    CompletableFuture<ChapterPage> bookChapters(String bookId, Query query);

    CompletableFuture<MoviePage> movie(String movieId);

    CompletableFuture<MoviePage> movies();

    CompletableFuture<MoviePage> movies(Query query);

    CompletableFuture<QuotePage> movieQuotes(String movieId, Query query);

    CompletableFuture<QuotePage> quotes(Query query);

    CompletableFuture<QuotePage> quote(String quoteId);

    CompletableFuture<QuotePage> quotes();

    CompletableFuture<ChapterPage> chapters(Query query);

    CompletableFuture<ChapterPage> chapter(String chapterId);

    CompletableFuture<ChapterPage> chapters();

    CompletableFuture<CharacterPage> characters();

    CompletableFuture<CharacterPage> characters(Query query);

    CompletableFuture<CharacterPage> character(String characterId);

    CompletableFuture<QuotePage> characterQuotes(String characterId, Query query);
}
