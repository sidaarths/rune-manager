package persistence;

import model.Rune;
import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {
    protected void checkRune(Rune rune, String title, String primaryTree, String secondaryTree, String keystoneRune) {
        assertEquals(title, rune.getTitle());
        assertEquals(primaryTree, rune.getPrimaryTree());
        assertEquals(secondaryTree, rune.getSecondaryTree());
        assertEquals(keystoneRune, rune.getKeystoneRune());
    }
}
