import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.TestConstants.BEARER_TOKEN;

import io.github.skosijer.lotr.LotrClient;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;

class SingleQuoteTests {

    @Test
    void shouldReturnQuoteDialog_WhenSingleQuoteWithValidIdRequested() throws ExecutionException, InterruptedException {
        var lotrClient = new LotrClient(BEARER_TOKEN);
        var expected = "Give us that! Deagol my love";

        var actual = lotrClient.quote("5cd96e05de30eff6ebcce7ec").get()
            .getDocs().get(0).getDialog();

        assertEquals(expected, actual);
    }
}
