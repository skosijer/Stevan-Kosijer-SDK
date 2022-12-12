package io.github.skosijer.lotr.service.impl;

import io.github.skosijer.lotr.api.request.Query;
import io.github.skosijer.lotr.api.response.QuotePage;
import io.github.skosijer.lotr.client.GetAllClient;
import io.github.skosijer.lotr.client.GetByIdClient;
import io.github.skosijer.lotr.service.IQuoteService;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QuoteService implements IQuoteService {

    private static final String QUOTE_RESOURCE = "quote";

    private final GetByIdClient<QuotePage> getByIdClient = new GetByIdClient<>(QuotePage.class, QUOTE_RESOURCE);

    private final GetAllClient<QuotePage> getAllClient = new GetAllClient<>(QuotePage.class, QUOTE_RESOURCE);

    public CompletableFuture<QuotePage> getQuote(String id) {
        return getByIdClient.getById(id);
    }

    public CompletableFuture<QuotePage> getQuotes(Query query) {
        return getAllClient.getAll(query);
    }
}
