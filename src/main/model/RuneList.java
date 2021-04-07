package model;

import model.exceptions.NotFoundException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

//Represents a list of rune pages
public class RuneList implements Writable {
    private ArrayList<Rune> runePageList;

    //EFFECTS: Creates new empty list
    public RuneList() {
        runePageList = new ArrayList<>();
    }

    //EFFECTS: returns true if the queue is empty, false otherwise
    public boolean isEmpty() {
        return runePageList.isEmpty();
    }

    //EFFECTS: returns number of rune pages in list
    public int length() {
        return runePageList.size();
    }

    //EFFECTS: produces the list of rune pages
    public ArrayList<Rune> getList() {
        return runePageList;
    }

    //MODIFIES: this
    //EFFECTS: adds a rune page to list
    public void addRune(Rune rune) {
        runePageList.add(rune);
    }

    //MODIFIES: this
    //EFFECTS: deletes a rune page with given title from list
    public void deleteRune(String title) {
        ArrayList<Rune> newList = new ArrayList<>();
        for (Rune r: runePageList) {
            if (!r.getTitle().equals(title)) {
                newList.add(r);
            }
        }
        runePageList = newList;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("pages", runesToJson());
        return json;
    }

    //EFFECTS: returns rune pages in this list as a JSON array
    private JSONArray runesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Rune r : runePageList) {
            jsonArray.put(r.toJson());
        }

        return jsonArray;
    }

    //EFFECTS: returns a new rune page list of rune pages with given keystone
    // throws NotFoundException if no rune pages were found
    public RuneList sortWithKey(String keyR) throws NotFoundException {
        int flag = 0;
        RuneList newList = new RuneList();
        for (Rune r : runePageList) {
            if (r.getKeystoneRune().equals(keyR)) {
                flag = 1;
                newList.addRune(r);
            }
        }
        if (flag == 0) {
            throw new NotFoundException("Rune Page with given keystone not found.");
        }
        return newList;
    }
}

