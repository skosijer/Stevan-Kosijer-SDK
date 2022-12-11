import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.TestConstants.BEARER_TOKEN;

import io.github.skosijer.lotr.LotrClient;
import io.github.skosijer.lotr.api.request.Filter;
import io.github.skosijer.lotr.api.request.FilterType;
import io.github.skosijer.lotr.api.request.Query;
import io.github.skosijer.lotr.api.request.Sort;
import io.github.skosijer.lotr.api.request.SortOrder;
import io.github.skosijer.lotr.api.response.Book;
import io.github.skosijer.lotr.api.response.BookPage;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;

class BookFilterTests {

    @Test
    void shouldReturnBookThatMatchesId_WhenMatchFilterIsPassed() throws ExecutionException, InterruptedException {
        var lotrClient = new LotrClient(BEARER_TOKEN);
        var expected = BookPage.builder()
            .docs(List.of(
                Book.builder()
                    .id("5cf58077b53e011a64671583")
                    .name("The Two Towers")
                    .build()))
            .limit(1000)
            .total(1)
            .offset(0)
            .page(1)
            .pages(1)
            .build();

        var actual = lotrClient.books(Query.builder()
                .sort(Sort.builder()
                    .field("name")
                    .sortOrder(SortOrder.ASC)
                    .build())
                .filters(List.of(Filter.builder()
                    .field("_id")
                    .filterType(FilterType.MATCH)
                    .value("5cf58077b53e011a64671583")
                    .build()))
                .build())
            .get();

        assertEquals(expected, actual);
    }

    @Test
    void shouldNotReturnAnyBooks_WhenExistIdFilterIsPassed() throws ExecutionException, InterruptedException {
        var lotrClient = new LotrClient(BEARER_TOKEN);
        var expected = 0;

        var actual = lotrClient.books(Query.builder()
                .sort(Sort.builder()
                    .field("name")
                    .sortOrder(SortOrder.ASC)
                    .build())
                .filters(List.of(Filter.builder()
                    .field("_id")
                    .filterType(FilterType.NOT_EXIST)
                    .build()))
                .build())
            .get()
            .getDocs()
            .size();

        assertEquals(expected, actual);
    }
}
