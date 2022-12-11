import io.github.skosijer.lotr.LotrClient;
import io.github.skosijer.lotr.api.response.BookResponse;
import io.github.skosijer.lotr.api.response.BookPageResponse;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;


class LotrClientTest {
    
    @Test
    void shouldReturnBook_WhenValidBookIdPassed() throws ExecutionException, InterruptedException {
        var lotrClient = new LotrClient();
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

        var actual = lotrClient.getBooks("5cf58080b53e011a64671584").get();

        assertEquals(expected, actual);
    }
}
