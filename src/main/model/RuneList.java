package model;

import java.util.ArrayList;

//Represents a list of rune pages
public class RuneList {
    private ArrayList<Rune> lst;

    //EFFECTS: Creates new empty list
    public RuneList() {
        lst = new ArrayList<>();
    }

    //EFFECTS: returns true if the queue is empty, false otherwise
    public boolean isEmpty() {
        return lst.isEmpty();
    }

    //EFFECTS: returns number of rune pages in list
    public int length() {
        return lst.size();
    }

    //MODIFIES: this
    //EFFECTS: adds a rune page to list
    public void addRune(Rune rune) {
        lst.add(rune);
    }

    //EFFECTS: produces true and displays titles and keystone runes of all pages or false if list is empty
    public boolean displayLst() {
        if (isEmpty()) {
            return false;
        } else {
            for (Rune r:lst) {
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
            for (Rune r:lst) {
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
            for (Rune r : lst) {
                if ((r.getTitle()).equals(tit)) {
                    r.displayRune();
                    return true;
                }
            }
        }
        return false;
    }

}
