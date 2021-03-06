package persistence;


import model.Rune;
import model.RuneList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            RuneList rl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyRuneList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyRuneList.json");
        try {
            RuneList rl = reader.read();
            assertEquals(rl.length(), 0);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralRuneList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralRuneList.json");
        try {
            RuneList rl = reader.read();
            ArrayList<Rune> list =  rl.getList();
            assertEquals(2, rl.length());
            checkRune(list.get(0), "tit1", "primT1", "secT1", "key1");
            checkRune(list.get(1), "tit2", "primT2", "secT2", "key2");
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}