import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.TestConstants.BEARER_TOKEN;

import io.github.skosijer.lotr.LotrClient;
import io.github.skosijer.lotr.api.request.Query;
import io.github.skosijer.lotr.api.request.Sort;
import io.github.skosijer.lotr.api.request.SortOrder;
import io.github.skosijer.lotr.api.response.Book;
import io.github.skosijer.lotr.api.response.BookPage;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;

class BookSortTests {

    @Test
    void shouldReturnSortedBooks_WhenBooksWithSortRequested() throws ExecutionException, InterruptedException {
        var lotrClient = new LotrClient(BEARER_TOKEN);
        var expected = BookPage.builder()
            .docs(List.of(
                Book.builder()
                    .id("5cf5805fb53e011a64671582")
                    .name("The Fellowship Of The Ring")
                    .build(),
                Book.builder()
                    .id("5cf58080b53e011a64671584")
                    .name("The Return Of The King")
                    .build(),
                Book.builder()
                    .id("5cf58077b53e011a64671583")
                    .name("The Two Towers")
                    .build()))
            .limit(1000)
            .total(3)
            .offset(0)
            .page(1)
            .pages(1)
            .build();

        var actual = lotrClient.books(Query.builder()
                .sort(Sort.builder()
                    .field("name")
                    .sortOrder(SortOrder.ASC)
                    .build())
                .build())
            .get();

        assertEquals(expected, actual);
    }
}
