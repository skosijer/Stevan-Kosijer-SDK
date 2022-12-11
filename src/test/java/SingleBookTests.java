import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.TestConstants.BEARER_TOKEN;

import io.github.skosijer.lotr.LotrClient;
import io.github.skosijer.lotr.api.response.Book;
import io.github.skosijer.lotr.api.response.BookPage;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;

class SingleBookTests {

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
}
