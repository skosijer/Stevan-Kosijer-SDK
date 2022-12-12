package io.github.skosijer.lotr.service;

import io.github.skosijer.lotr.api.request.Query;
import io.github.skosijer.lotr.api.response.QuotePage;
import java.util.concurrent.CompletableFuture;

public interface IQuoteService {

    CompletableFuture<QuotePage> getQuote(String id);

    CompletableFuture<QuotePage> getQuotes(Query query);
}
