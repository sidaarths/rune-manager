package model;

import org.json.JSONObject;
import persistence.Writable;

//Represents one rune page
public class Rune implements Writable {
    private String title;
    private String primaryTree;
    private String secondaryTree;
    private String keystoneRune;
    private String prim2;
    private String prim3;
    private String prim4;
    private String sec1;
    private String sec2;

    // EFFECTS: rune page has given title, primary and secondary trees, and runes in them
    public Rune(String title, String primaryTree, String secondaryTree, String keystoneRune, String prim2, String prim3,
                String prim4, String sec1, String sec2) {
        this.title = title;
        this.primaryTree = primaryTree;
        this.secondaryTree = secondaryTree;
        this.keystoneRune = keystoneRune;
        this.prim2 = prim2;
        this.prim3 = prim3;
        this.prim4 = prim4;
        this.sec1 = sec1;
        this.sec2 = sec2;
    }

    //EFFECTS: returns title
    public String getTitle() {
        return title;
    }

    //EFFECTS: returns primary tree
    public String getPrimaryTree() {
        return primaryTree;
    }

    //EFFECTS: returns secondary tree
    public String getSecondaryTree() {
        return secondaryTree;
    }

    //EFFECTS: returns keystone rune
    public String getKeystoneRune() {
        return keystoneRune;
    }

    public void displayRune() {
        System.out.println(primaryTree + ": ");
        System.out.println("\t-" + keystoneRune);
        System.out.println("\t-" + prim2);
        System.out.println("\t-" + prim3);
        System.out.println("\t-" + prim4);
        System.out.println(secondaryTree + ": ");
        System.out.println("\t-" + sec1);
        System.out.println("\t-" + sec2);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("primaryTree", primaryTree);
        json.put("secondaryTree", secondaryTree);
        json.put("keystoneRune", keystoneRune);
        json.put("prim2", prim2);
        json.put("prim3", prim3);
        json.put("prim4", prim4);
        json.put("sec1", sec1);
        json.put("sec2", sec2);
        return json;
    }
}
