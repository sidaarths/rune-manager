package ui;


import model.Rune;
import model.RuneList;
import java.util.Scanner;

//Rune Manager Application
public class RuneApp {
    private RuneList runeList;
    private Scanner input;

    //EFFECTS: runs rune application
    public RuneApp() {
        runeList = new RuneList();
        input = new Scanner(System.in);
        runApp();
    }

    //MODIFIES: this
    //EFFECTS: processes user input
    private void runApp() {
        boolean keepGoing = true;
        String command;

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: processes user command

    private void processCommand(String command) {
        if (command.equals("a")) {
            doAdd();
        } else if (command.equals("d")) {
            doDisplay();
        } else if (command.equals("k")) {
            doKeyDisplay();
        } else if (command.equals("s")) {
            doSearch();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add new rune page");
        System.out.println("\td -> display all rune pages");
        System.out.println("\tk -> list rune pages with specific keystone");
        System.out.println("\ts -> search for a rune page with title");
        System.out.println("\tq -> quit");
    }

    //MODIFIES: this
    //EFFECTS: adds a rune page to list
    private void doAdd() {
        System.out.println("Enter title for new rune page: ");
        String title = input.nextLine();
        System.out.println("Enter primary tree: ");
        String primR = input.nextLine();
        System.out.println("Enter secondary tree: ");
        String secR = input.nextLine();
        System.out.println("Enter keystone rune: ");
        String keyR = input.nextLine();
        System.out.println("Enter primary tree's 2nd rune: ");
        String prim2 = input.nextLine();
        System.out.println("Enter primary tree's 3rd rune: ");
        String prim3 = input.nextLine();
        System.out.println("Enter primary tree's 4th rune: ");
        String prim4 = input.nextLine();
        System.out.println("Enter secondary tree's 1st rune: ");
        String sec1 = input.nextLine();
        System.out.println("Enter secondary tree's 2nd rune: ");
        String sec2 = input.nextLine();
        Rune rune = new Rune(title, primR, secR, keyR, prim2, prim3, prim4, sec1, sec2);
        runeList.addRune(rune);
    }

    //EFFECTS: displays all rune pages' titles and keystone runes
    private void doDisplay() {
        if (runeList.displayLst()) {
            runeList.displayLst();
        } else {
            System.out.println("The list is empty.");
        }
    }

    //EFFECTS: displays all rune pages' titles and keystone runes with specified keystone
    private void doKeyDisplay() {
        System.out.println("Enter keystone rune to search for: ");
        String keyR = input.nextLine();
        if (runeList.displayLstWithKey(keyR)) {
            runeList.displayLstWithKey(keyR);
        } else {
            System.out.println("No rune page with specified keystone found.");
        }
    }

    //EFFECTS: search for a rune page with a specific title
    private void doSearch() {
        System.out.println("Enter title to search for: ");
        String tit = input.nextLine();
        if (runeList.displayRuneWithTitle(tit)) {
            runeList.displayRuneWithTitle(tit);
        } else {
            System.out.println("No rune page with specified title found.");
        }
    }
}

