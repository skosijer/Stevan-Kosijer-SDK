package io.github.skosijer.lotr.service.impl;

import io.github.skosijer.lotr.api.request.Query;
import io.github.skosijer.lotr.api.response.MoviePage;
import io.github.skosijer.lotr.api.response.QuotePage;
import io.github.skosijer.lotr.client.GetAllByIdClient;
import io.github.skosijer.lotr.client.GetAllClient;
import io.github.skosijer.lotr.client.GetByIdClient;
import io.github.skosijer.lotr.service.IMovieService;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MovieService implements IMovieService {

    private static final String MOVIE_RESOURCE = "movie";

    private static final String QUOTE_RESOURCE = "quote";

    private final GetByIdClient<MoviePage> getByIdClient = new GetByIdClient<>(MoviePage.class, MOVIE_RESOURCE);

    private final GetAllClient<MoviePage> getAllClient = new GetAllClient<>(MoviePage.class, MOVIE_RESOURCE);

    private final GetAllByIdClient<QuotePage> getAllByIdClient = new GetAllByIdClient<>(QuotePage.class,
        MOVIE_RESOURCE,
        QUOTE_RESOURCE);

    public CompletableFuture<MoviePage> getMovie(String id) {
        return getByIdClient.getById(id);
    }

    public CompletableFuture<MoviePage> getMovies(Query query) {
        return getAllClient.getAll(query);
    }

    public CompletableFuture<QuotePage> getMovieQuotes(String id, Query query) {
        return getAllByIdClient.getAllById(id, query);
    }
}
