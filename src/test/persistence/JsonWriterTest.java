package persistence;


import model.Rune;
import model.RuneList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            RuneList rl = new RuneList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyRuneList() {
        try {
            RuneList runeList = new RuneList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyRuneList.json");
            writer.open();
            writer.write(runeList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyRuneList.json");
            runeList = reader.read();
            assertTrue(runeList.isEmpty());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralRuneList() {
        try {
            RuneList runeList = new RuneList();
            Rune rune1 = new Rune("title", "primaryTree", "secondaryTree", "keystoneRune",
                    "prim2", "prim3", "prim4", "sec1", "sec2");
            Rune rune2 = new Rune("title2", "primaryTree2", "secondaryTree2"
                    , "keystoneRune2", "prim2", "prim3", "prim4", "sec1", "sec2");
            runeList.addRune(rune1);
            runeList.addRune(rune2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralRuneList.json");
            writer.open();
            writer.write(runeList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralRuneList.json");
            runeList = reader.read();
            List<Rune> runes = runeList.toList();
            assertEquals(2, runes.size());
            checkRune(runes.get(0), "title", "primaryTree", "secondaryTree", "keystoneRune");
            checkRune(runes.get(1), "title2", "primaryTree2", "secondaryTree2", "keystoneRune2");

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}