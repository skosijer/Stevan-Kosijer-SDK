import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.skosijer.lotr.LotrClient;
import io.github.skosijer.lotr.api.request.Pagination;
import io.github.skosijer.lotr.api.request.Query;
import io.github.skosijer.lotr.api.request.Sort;
import io.github.skosijer.lotr.api.request.SortOrder;
import io.github.skosijer.lotr.api.response.Book;
import io.github.skosijer.lotr.api.response.BookPage;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;

class LotrClientIT {

    private static final String BEARER_TOKEN = "GauQBaZomieQ6DKqQLZA";

    @Test
    void shouldReturnBooks_WhenAllBooksRequested() throws ExecutionException, InterruptedException {
        var lotrClient = new LotrClient(BEARER_TOKEN);
        var expected = BookPage.builder()
            .docs(List.of(
                Book.builder()
                    .id("5cf5805fb53e011a64671582")
                    .name("The Fellowship Of The Ring")
                    .build(),
                Book.builder()
                    .id("5cf58077b53e011a64671583")
                    .name("The Two Towers")
                    .build(),
                Book.builder()
                    .id("5cf58080b53e011a64671584")
                    .name("The Return Of The King")
                    .build()))
            .limit(1000)
            .total(3)
            .offset(0)
            .page(1)
            .pages(1)
            .build();

        var actual = lotrClient.books().get();

        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnBook_WhenSingleBookWithValidIdRequested() throws ExecutionException, InterruptedException {
        var lotrClient = new LotrClient(BEARER_TOKEN);
        var expected = BookPage.builder()
            .docs(List.of(Book.builder()
                .id("5cf58080b53e011a64671584")
                .name("The Return Of The King")
                .build()))
            .limit(1000)
            .total(1)
            .offset(0)
            .page(1)
            .pages(1)
            .build();

        var actual = lotrClient.book("5cf58080b53e011a64671584").get();

        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnSingleBookChapters_WhenBookChaptersWithLimitOneRequested()
        throws ExecutionException, InterruptedException {
        var lotrClient = new LotrClient(BEARER_TOKEN);
        var expected = 1;

        var actual = lotrClient.bookChapters("5cf58080b53e011a64671584", Query.builder()
                .pagination(Pagination.builder()
                    .limit(1)
                    .build())
                .build())
            .get()
            .getDocs().size();

        assertEquals(expected, actual);
    }

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
