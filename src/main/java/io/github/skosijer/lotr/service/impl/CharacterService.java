package io.github.skosijer.lotr.service.impl;

import io.github.skosijer.lotr.api.request.Query;
import io.github.skosijer.lotr.api.response.CharacterPage;
import io.github.skosijer.lotr.api.response.QuotePage;
import io.github.skosijer.lotr.client.GetAllByIdClient;
import io.github.skosijer.lotr.client.GetAllClient;
import io.github.skosijer.lotr.client.GetByIdClient;
import io.github.skosijer.lotr.service.ICharacterService;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CharacterService implements ICharacterService {

    private static final String CHARACTER_RESOURCE = "character";

    private static final String QUOTE_RESOURCE = "quote";

    private final GetByIdClient<CharacterPage> getByIdClient = new GetByIdClient<>(CharacterPage.class,
        CHARACTER_RESOURCE);

    private final GetAllClient<CharacterPage> getAllClient = new GetAllClient<>(CharacterPage.class,
        CHARACTER_RESOURCE);

    private final GetAllByIdClient<QuotePage> getAllByIdClient = new GetAllByIdClient<>(QuotePage.class,
        CHARACTER_RESOURCE,
        QUOTE_RESOURCE);

    @Override
    public CompletableFuture<CharacterPage> getCharacter(String id) {
        return getByIdClient.getById(id);
    }

    @Override
    public CompletableFuture<CharacterPage> getCharacters(Query query) {
        return getAllClient.getAll(query);
    }

    @Override
    public CompletableFuture<QuotePage> getCharacterQuotes(String id, Query query) {
        return getAllByIdClient.getAllById(id, query);
    }
}
