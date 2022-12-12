import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.TestConstants.BEARER_TOKEN;

import io.github.skosijer.lotr.LotrClient;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;

class SingleMovieTests {

    @Test
    void shouldReturnMovie_WhenSingleMovieWithValidIdRequested() throws ExecutionException, InterruptedException {
        var lotrClient = new LotrClient(BEARER_TOKEN);
        var expected = "The Lord of the Rings Series";

        var actual = lotrClient.movie("5cd95395de30eff6ebccde56").get()
            .getDocs().get(0).getName();

        assertEquals(expected, actual);
    }
}
