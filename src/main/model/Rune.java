package model;

//Represents one rune page
public class Rune {
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
    public Rune(String tit, String primR, String secR, String keyR, String pr2, String pr3, String pr4,
                String sc1, String sc2) {
        this.title = tit;
        this.primaryTree = primR;
        this.secondaryTree = secR;
        this.keystoneRune = keyR;
        this.prim2 = pr2;
        this.prim3 = pr3;
        this.prim4 = pr4;
        this.sec1 = sc1;
        this.sec2 = sc2;
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
        System.out.println("\t" + primaryTree + "\t" + secondaryTree);
        System.out.println("\t" + keystoneRune + "\t" + sec1);
        System.out.println("\t" + prim2 + "\t" + sec2);
        System.out.println("\t" + prim3);
        System.out.println("\t" + prim4);
    }
}
