import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.TestConstants.BEARER_TOKEN;

import io.github.skosijer.lotr.LotrClient;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;

class SingleChapterTests {

    @Test
    void shouldReturnChapterDialog_WhenSingleChapterWithValidIdRequested()
        throws ExecutionException, InterruptedException {
        var lotrClient = new LotrClient(BEARER_TOKEN);
        var expected = "A Long-expected Party";

        var actual = lotrClient.chapter("6091b6d6d58360f988133b8b").get()
            .getDocs().get(0).getChapterName();

        assertEquals(expected, actual);
    }
}
