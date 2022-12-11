import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.TestConstants.BEARER_TOKEN;

import io.github.skosijer.lotr.LotrClient;
import io.github.skosijer.lotr.api.request.Pagination;
import io.github.skosijer.lotr.api.request.Query;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;

class BookChaptersTest {

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
}
