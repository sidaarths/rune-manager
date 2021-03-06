package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RuneListTest {
    private RuneList runeList;
    private Rune rune1;
    private Rune rune2;

    @BeforeEach
    public void runBefore() {
        runeList = new RuneList();
        rune1 = new Rune("title", "primaryTree", "secondaryTree", "keystoneRune",
                "prim2", "prim3", "prim4", "sec1", "sec2");
        rune2 = new Rune("title2", "primaryTree2", "secondaryTree2"
                , "keystoneRune2", "prim2", "prim3", "prim4", "sec1", "sec2");
    }

    @Test
    public void testConstructorAndIsEmpty() {
        assertTrue(runeList.isEmpty());
        runeList.addRune(rune1);
        assertFalse(runeList.isEmpty());
    }

    @Test
    public void testAddRune() {
        runeList.addRune(rune1);
        assertEquals(runeList.length(), 1);
    }

    @Test
    public void testDisplayLst() {
        assertFalse(runeList.displayLst());
        runeList.addRune(rune1);
        assertTrue(runeList.displayLst());
    }

    @Test
    public void testDisplayLstWithKey() {
        assertFalse(runeList.displayLstWithKey("keystoneRune"));
        runeList.addRune(rune1);
        assertFalse(runeList.displayLstWithKey("keystoneRune2"));
        assertTrue(runeList.displayLstWithKey("keystoneRune"));
        runeList.addRune(rune2);
        assertTrue(runeList.displayLstWithKey("keystoneRune2"));
    }

    @Test
    public void testDisplayRuneWithTitle() {
        assertFalse(runeList.displayRuneWithTitle("title"));
        runeList.addRune(rune1);
        assertFalse(runeList.displayRuneWithTitle("title2"));
        assertTrue(runeList.displayRuneWithTitle("title"));
        runeList.addRune(rune2);
        assertTrue(runeList.displayRuneWithTitle("title2"));
    }

    @Test
    public void testDeleteRune() {
        runeList.addRune(rune1);
        assertEquals(runeList.length(), 1);
        runeList.deleteRune("title");
        assertTrue(runeList.isEmpty());
    }

    @Test
    public void testDeleteRuneWithTwoRunes() {
        runeList.addRune(rune1);
        assertEquals(runeList.length(), 1);
        runeList.addRune(rune2);
        assertEquals(runeList.length(), 2);
        runeList.deleteRune("title");
        assertEquals(runeList.length(), 1);
    }

    @Test
    public void testDeleteRuneWithNoDeletions() {
        runeList.addRune(rune1);
        assertEquals(runeList.length(), 1);
        runeList.addRune(rune2);
        assertEquals(runeList.length(), 2);
        runeList.deleteRune("title0");
        assertEquals(runeList.length(), 2);
    }

}