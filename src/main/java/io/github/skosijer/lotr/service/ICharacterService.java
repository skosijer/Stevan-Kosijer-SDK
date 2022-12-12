package io.github.skosijer.lotr.service;

import io.github.skosijer.lotr.api.request.Query;
import io.github.skosijer.lotr.api.response.CharacterPage;
import io.github.skosijer.lotr.api.response.QuotePage;
import java.util.concurrent.CompletableFuture;

public interface ICharacterService {

    CompletableFuture<CharacterPage> getCharacter(String id);

    CompletableFuture<CharacterPage> getCharacters(Query query);

    CompletableFuture<QuotePage> getCharacterQuotes(String id, Query query);
}
