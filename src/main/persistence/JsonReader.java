package persistence;

import model.Rune;
import model.RuneList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads runeList from JSON data stored in file
// Citation: code obtained from JsonSerializationDemo

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads rune list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public RuneList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseRuneList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses rune page list from JSON object and returns it
    private RuneList parseRuneList(JSONObject jsonObject) {
        RuneList runeList = new RuneList();
        addRunePages(runeList, jsonObject);
        return runeList;
    }

    // MODIFIES: runeList
    // EFFECTS: parses rune pages from JSON object and adds them to List
    private void addRunePages(RuneList rl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("pages");
        for (Object json : jsonArray) {
            JSONObject nextPage = (JSONObject) json;
            addRune(rl, nextPage);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses rune page from JSON object and adds it to list
    private void addRune(RuneList rl, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        String primaryTree = jsonObject.getString("primaryTree");
        String secondaryTree = jsonObject.getString("secondaryTree");
        String keystoneRune = jsonObject.getString("keystoneRune");
        String prim2 = jsonObject.getString("prim2");
        String prim3 = jsonObject.getString("prim3");
        String prim4 = jsonObject.getString("prim4");
        String sec1 = jsonObject.getString("sec1");
        String sec2 = jsonObject.getString("sec2");
        Rune rune = new Rune(title, primaryTree, secondaryTree, keystoneRune, prim2, prim3, prim4, sec1, sec2);
        rl.addRune(rune);
    }
}
