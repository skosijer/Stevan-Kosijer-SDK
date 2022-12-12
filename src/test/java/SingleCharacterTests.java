import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.TestConstants.BEARER_TOKEN;

import io.github.skosijer.lotr.LotrClient;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;

class SingleCharacterTests {

    @Test
    void shouldReturnCharacter_WhenSingleCharacterWithValidIdRequested()
        throws ExecutionException, InterruptedException {
        var lotrClient = new LotrClient(BEARER_TOKEN);
        var expected = "Algund";

        var actual = lotrClient.character("5cd99d4bde30eff6ebccfbc5").get()
            .getDocs().get(0).getName();

        assertEquals(expected, actual);
    }
}
