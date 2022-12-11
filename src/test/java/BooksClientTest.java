import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.skosijer.lotr.LotrClient;
import io.github.skosijer.lotr.api.response.BookPageResponse;
import io.github.skosijer.lotr.api.response.BookResponse;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;

class BooksClientTest {

    @Test
    void shouldReturnBooks_WhenAllBooksRequested() throws ExecutionException, InterruptedException {
        var lotrClient = new LotrClient("GauQBaZomieQ6DKqQLZA");
        var expected = BookPageResponse.builder()
            .docs(List.of(
                BookResponse.builder()
                    .id("5cf5805fb53e011a64671582")
                    .name("The Fellowship Of The Ring")
                    .build(),
                BookResponse.builder()
                    .id("5cf58077b53e011a64671583")
                    .name("The Two Towers")
                    .build(),
                BookResponse.builder()
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
        var lotrClient = new LotrClient("5cf58080b53e011a64671584");
        var expected = BookPageResponse.builder()
            .docs(List.of(BookResponse.builder()
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
}
