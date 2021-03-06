package model;

import model.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
        ArrayList<Rune> list = runeList.getList();
        assertEquals(list.get(0), rune1);
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
        ArrayList<Rune> list = runeList.getList();
        assertEquals(list.get(0), rune2);
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

    @Test
    public void testSortWithoutException() {
        try {
            runeList.addRune(rune1);
            runeList.addRune(rune2);
            Rune rune3 = new Rune("title45", "primaryTree45", "secondaryTree45",
                    "keystoneRune", "prim2", "prim3", "prim4", "sec1", "sec2");
            runeList.addRune(rune3);
            RuneList newList = runeList.sortWithKey("keystoneRune");
            assertTrue(newList.getList().contains(rune1));
            assertTrue(newList.getList().contains(rune3));
        } catch (NotFoundException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void testSortWithException() {
        try {
            runeList.addRune(rune1);
            runeList.addRune(rune2);
            Rune rune3 = new Rune("title45", "primaryTree45", "secondaryTree45",
                    "keystoneRune", "prim2", "prim3", "prim4", "sec1", "sec2");
            runeList.addRune(rune3);
            runeList.sortWithKey("keystoneRune7");
            fail("Exception should be thrown");
        } catch (NotFoundException e) {
            //pass
        }
    }
}