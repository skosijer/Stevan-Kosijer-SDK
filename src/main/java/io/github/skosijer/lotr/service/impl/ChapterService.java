package io.github.skosijer.lotr.service.impl;

import io.github.skosijer.lotr.api.request.Query;
import io.github.skosijer.lotr.api.response.ChapterPage;
import io.github.skosijer.lotr.client.GetAllClient;
import io.github.skosijer.lotr.client.GetByIdClient;
import io.github.skosijer.lotr.service.IChapterService;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChapterService implements IChapterService {

    private static final String CHAPTER_RESOURCE = "chapter";

    private final GetByIdClient<ChapterPage> getByIdClient = new GetByIdClient<>(ChapterPage.class, CHAPTER_RESOURCE);

    private final GetAllClient<ChapterPage> getAllClient = new GetAllClient<>(ChapterPage.class, CHAPTER_RESOURCE);

    public CompletableFuture<ChapterPage> getChapter(String id) {
        return getByIdClient.getById(id);
    }

    public CompletableFuture<ChapterPage> getChapters(Query query) {
        return getAllClient.getAll(query);
    }
}
