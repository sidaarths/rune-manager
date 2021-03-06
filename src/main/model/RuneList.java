package model;

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

    //EFFECTS: produces true and displays titles and keystone runes of all pages or false if list is empty
    public boolean displayLst() {
        if (isEmpty()) {
            return false;
        } else {
            for (Rune r: runePageList) {
                System.out.println(r.getTitle() + " - " + r.getKeystoneRune() + " (" + r.getPrimaryTree() + ")" + " - "
                        + r.getSecondaryTree());
            }
            return true;
        }
    }

    //EFFECTS: produces true and displays titles and keystone runes of all pages with specified keystone or false if
    // there are none
    public boolean displayLstWithKey(String keyR) {
        int flag = 0;
        if (isEmpty()) {
            return false;
        } else {
            for (Rune r: runePageList) {
                if ((r.getKeystoneRune()).equals(keyR)) {
                    System.out.println(r.getTitle() + " - " + r.getKeystoneRune() + " (" + r.getPrimaryTree() + ")"
                            + " - " + r.getSecondaryTree());
                    flag = 1;
                }
            }
            return flag != 0;
        }
    }

    //EFFECTS: produces true and displays rune page with specified title or false if not found
    public boolean displayRuneWithTitle(String tit) {
        if (!isEmpty()) {
            for (Rune r : runePageList) {
                if ((r.getTitle()).equals(tit)) {
                    r.displayRune();
                    return true;
                }
            }
        }
        return false;
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
}
