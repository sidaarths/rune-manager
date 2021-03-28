package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RuneTest {
    private Rune rune;

    @BeforeEach
    public void runBefore() {
        rune = new Rune("title", "primaryTree", "secondaryTree", "keystoneRune",
                "prim2", "prim3", "prim4", "sec1", "sec2");
    }

    @Test
    public void testConstructor() {
        assertEquals(rune.getTitle(), "title");
        assertEquals(rune.getPrimaryTree(), "primaryTree");
        assertEquals(rune.getSecondaryTree(), "secondaryTree");
        assertEquals(rune.getKeystoneRune(), "keystoneRune");
    }

    @Test
    public void testDisplayRune() {
        assertEquals("primaryTree:   - keystoneRune  - prim2  - prim3  - prim4              secondaryTree:   - " +
                "sec1  - sec2", rune.displayRune());
    }
}