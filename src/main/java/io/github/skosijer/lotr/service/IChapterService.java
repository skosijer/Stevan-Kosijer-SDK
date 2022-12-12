package io.github.skosijer.lotr.service;

import io.github.skosijer.lotr.api.request.Query;
import io.github.skosijer.lotr.api.response.ChapterPage;
import java.util.concurrent.CompletableFuture;

public interface IChapterService {

    CompletableFuture<ChapterPage> getChapter(String id);

    CompletableFuture<ChapterPage> getChapters(Query query);
}
