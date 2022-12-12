package io.github.skosijer.lotr.service;

import io.github.skosijer.lotr.api.request.Query;
import io.github.skosijer.lotr.api.response.MoviePage;
import io.github.skosijer.lotr.api.response.QuotePage;
import java.util.concurrent.CompletableFuture;

public interface IMovieService {

    CompletableFuture<MoviePage> getMovie(String id);

    CompletableFuture<MoviePage> getMovies(Query query);

    CompletableFuture<QuotePage> getMovieQuotes(String id, Query query);
}
