package io.github.skosijer.lotr;

import io.github.skosijer.lotr.api.request.Query;
import io.github.skosijer.lotr.api.response.BookPage;
import io.github.skosijer.lotr.api.response.ChapterPage;
import io.github.skosijer.lotr.api.response.CharacterPage;
import io.github.skosijer.lotr.api.response.MoviePage;
import io.github.skosijer.lotr.api.response.QuotePage;
import io.github.skosijer.lotr.client.HttpClientWithToken;
import io.github.skosijer.lotr.exception.EntityIdNullException;
import io.github.skosijer.lotr.service.IBookService;
import io.github.skosijer.lotr.service.IChapterService;
import io.github.skosijer.lotr.service.ICharacterService;
import io.github.skosijer.lotr.service.IMovieService;
import io.github.skosijer.lotr.service.IQuoteService;
import io.github.skosijer.lotr.service.impl.BookService;
import io.github.skosijer.lotr.service.impl.ChapterService;
import io.github.skosijer.lotr.service.impl.CharacterService;
import io.github.skosijer.lotr.service.impl.MovieService;
import io.github.skosijer.lotr.service.impl.QuoteService;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/**
 * Use this Lotr API implementation to execute all the endpoints and variations that Lotr API offers.
 */
public class LotrClient implements ILotrClient {

    public LotrClient(String bearerToken) {
        HttpClientWithToken.initialize(bearerToken);
    }

    private final IBookService bookService = new BookService();

    private final IMovieService movieService = new MovieService();

    private final IQuoteService quoteService = new QuoteService();

    private final IChapterService chapterService = new ChapterService();

    private final ICharacterService characterService = new CharacterService();

    public CompletableFuture<BookPage> books() {
        return bookService.getBooks(null);
    }

    public CompletableFuture<BookPage> books(Query query) {
        return bookService.getBooks(query);
    }

    public CompletableFuture<BookPage> book(String bookId) {
        if (Objects.isNull(bookId)) {
            return CompletableFuture.failedFuture(new EntityIdNullException());
        }

        return bookService.getBook(bookId);
    }

    public CompletableFuture<ChapterPage> bookChapters(String bookId, Query query) {
        if (Objects.isNull(bookId)) {
            return CompletableFuture.failedFuture(new EntityIdNullException());
        }

        return bookService.getBookChapters(bookId, query);
    }

    @Override
    public CompletableFuture<MoviePage> movie(String movieId) {
        if (Objects.isNull(movieId)) {
            return CompletableFuture.failedFuture(new EntityIdNullException());
        }

        return movieService.getMovie(movieId);
    }

    @Override
    public CompletableFuture<MoviePage> movies() {
        return movieService.getMovies(null);
    }

    @Override
    public CompletableFuture<MoviePage> movies(Query query) {
        return movieService.getMovies(query);
    }

    @Override
    public CompletableFuture<QuotePage> movieQuotes(String movieId, Query query) {
        if (Objects.isNull(movieId)) {
            return CompletableFuture.failedFuture(new EntityIdNullException());
        }

        return movieService.getMovieQuotes(movieId, query);
    }

    @Override
    public CompletableFuture<QuotePage> quotes(Query query) {
        return quoteService.getQuotes(query);
    }

    @Override
    public CompletableFuture<QuotePage> quotes() {
        return quoteService.getQuotes(null);
    }

    @Override
    public CompletableFuture<QuotePage> quote(String quoteId) {
        if (Objects.isNull(quoteId)) {
            return CompletableFuture.failedFuture(new EntityIdNullException());
        }

        return quoteService.getQuote(quoteId);
    }

    @Override
    public CompletableFuture<ChapterPage> chapters(Query query) {
        return chapterService.getChapters(query);
    }

    @Override
    public CompletableFuture<ChapterPage> chapter(String chapterId) {
        if (Objects.isNull(chapterId)) {
            return CompletableFuture.failedFuture(new EntityIdNullException());
        }

        return chapterService.getChapter(chapterId);
    }

    @Override
    public CompletableFuture<ChapterPage> chapters() {
        return chapterService.getChapters(null);
    }

    @Override
    public CompletableFuture<CharacterPage> characters() {
        return characterService.getCharacters(null);
    }

    @Override
    public CompletableFuture<CharacterPage> characters(Query query) {
        return characterService.getCharacters(query);
    }

    @Override
    public CompletableFuture<CharacterPage> character(String characterId) {
        if (Objects.isNull(characterId)) {
            return CompletableFuture.failedFuture(new EntityIdNullException());
        }
        
        return characterService.getCharacter(characterId);
    }

    @Override
    public CompletableFuture<QuotePage> characterQuotes(String characterId, Query query) {
        return characterService.getCharacterQuotes(characterId, query);
    }
}