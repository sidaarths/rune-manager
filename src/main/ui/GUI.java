package ui;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;

import model.Rune;
import model.RuneList;

import model.exceptions.NotFoundException;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

//GUI Main Application
public class GUI {

    private static final String JSON_STORE = "./data/myFile.json";
    private RuneList runeList;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JFrame frame;
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JButton save;
    private JTable table;
    private JButton add;
    private JButton delete;
    private JButton search;
    private JButton keySearch;

    //EFFECTS: runs rune application
    public GUI() {
        runeList = new RuneList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        String command;

        int flag = 0;
        while (flag == 0) {
            command = JOptionPane.showInputDialog(null, "Would you like to load rune page list from file? (Y/N)");
            command = command.toLowerCase();
            if (command.equals("y")) {
                loadRunePageList();
                flag = 1;
            } else if (command.equals("n")) {
                flag = 1;
            } else {
                JOptionPane.showMessageDialog(null, "Invalid option!");
            }
        }
        runApp();
    }

    //MODIFIES: this
    //EFFECTS: sets up GUI and loads file if needed
    public void runApp() {
        frameStart();

        componentSetup();

        mainPanel = new JPanel();
        topPanel = new JPanel();
        bottomPanel = new JPanel();

        topPanel.setPreferredSize(new Dimension(75,75));
        topPanel.add(save);
        mainPanel.add(table);
        bottomPanel.add(add);
        bottomPanel.add(delete);
        bottomPanel.add(search);
        bottomPanel.add(keySearch);

        frameSetup();
    }

    //MODIFIES: this
    //EFFECTS: setups up the components of the main frame
    private void componentSetup() {
        save = new JButton("Save");
        SaveButtonListener saveListener = new SaveButtonListener();
        save.addActionListener(saveListener);

        table = makeTable(runeList);

        add = new JButton("Add");
        AddButtonListener addButtonListener = new AddButtonListener();
        add.addActionListener(addButtonListener);

        search = new JButton("Search");
        SearchButtonListener searchButtonListener = new SearchButtonListener();
        search.addActionListener(searchButtonListener);

        delete = new JButton("Delete");
        DeleteButtonListener deleteButtonListener = new DeleteButtonListener();
        delete.addActionListener(deleteButtonListener);

        keySearch = new JButton("Sort with Keystone");
        KeyButtonListener keyButtonListener = new KeyButtonListener();
        keySearch.addActionListener(keyButtonListener);
    }

    //MODIFIES: this
    //EFFECTS: starts frame
    private void frameStart() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("LoL Rune Page Manager");
        frame.setSize(500,500);
        frame.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: adds panels to frame
    private void frameSetup() {
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: loads rune page list from file
    private void loadRunePageList() {
        try {
            runeList = jsonReader.read();
            System.out.println("Loaded list from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //EFFECTS: makes data table
    private JTable makeTable(RuneList runeList) {
        JTable table = new JTable(runeList.getList().size() + 1,4);
        table.setValueAt("TITLE", 0, 0);
        table.setValueAt("TREE 1", 0, 1);
        table.setValueAt("KEY RUNE", 0, 2);
        table.setValueAt("TREE 2", 0, 3);
        int row = 1;
        for (Rune rune: runeList.getList()) {
            table.setValueAt(rune.getTitle(), row, 0);
            table.setValueAt(rune.getPrimaryTree(), row, 1);
            table.setValueAt(rune.getKeystoneRune(), row, 2);
            table.setValueAt(rune.getSecondaryTree(), row, 3);
            row++;
        }
        return table;
    }

    //listener for save button
    private class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                jsonWriter.open();
                jsonWriter.write(runeList);
                jsonWriter.close();
                JOptionPane.showMessageDialog(null, "File saved");
                playSound();
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Unable to write to file");
            }
        }

        //MODIFIES: this
        //EFFECTS: plays sound
        public void playSound() {
            File audioFile = new File("./data/soundForSave.wav");
            try {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
                AudioFormat format = audioStream.getFormat();
                DataLine.Info info = new DataLine.Info(Clip.class, format);
                Clip audioClip = (Clip) AudioSystem.getLine(info);
                audioClip.open(audioStream);
                audioClip.start();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    System.out.println("Thread sleep interrupted");
                }
                audioClip.close();
                audioStream.close();
            } catch (UnsupportedAudioFileException ex) {
                System.out.println("Audio file not supported");
            } catch (LineUnavailableException ex) {
                System.out.println("Audio line unavailable");
            } catch (IOException ex) {
                System.out.println("IO Error");
            }
        }
    }

    //listener for search button
    private class SearchButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            String title = JOptionPane.showInputDialog(null, "Enter title: ");
            int flag = 0;
            for (Rune r : runeList.getList()) {
                if (r.getTitle().equals(title)) {
                    flag = 1;
                    JLabel info = new JLabel(r.displayRune());
                    JFrame resultFrame = new JFrame();
                    resultFrame.setTitle("Search Result for " + title);
                    resultFrame.setSize(750,500);
                    resultFrame.setVisible(true);
                    JPanel panel = new JPanel();
                    panel.add(info);
                    resultFrame.add(panel);
                }
            }
            if (flag == 0) {
                JOptionPane.showMessageDialog(null, "No rune page found with given title!");
            }
        }
    }

    //listener for key button
    private class KeyButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            String keyR = JOptionPane.showInputDialog(null, "Enter keystone rune: ");
            try {
                RuneList newList = runeList.sortWithKey(keyR);
                JTable resultTable = makeTable(newList);
                JFrame resultFrame = new JFrame();
                resultFrame.setTitle("Search Result for " + keyR);
                resultFrame.setSize(500,500);
                resultFrame.setVisible(true);
                JPanel panel = new JPanel();
                panel.add(resultTable);
                resultFrame.add(panel);
            } catch (NotFoundException e) {
                JOptionPane.showMessageDialog(null, "No rune page found with given title!");
            }
        }
    }

    //listener for delete button
    private class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            String title = JOptionPane.showInputDialog(null, "Enter title: ");
            int flag = 0;
            for (Rune r : runeList.getList()) {
                if (r.getTitle().equals(title)) {
                    flag = 1;
                    frame.dispose();
                    JOptionPane.showMessageDialog(null, "Rune page successfully deleted!");
                    runeList.deleteRune(r.getTitle());
                    runApp();
                }
            }
            if (flag == 0) {
                JOptionPane.showMessageDialog(null, "No rune page found with given title!");
            }
        }
    }

    //listener for add button
    public class AddButtonListener implements ActionListener {

        private JFrame addFrame;
        private JButton back;
        private JLabel titleLabel;
        private JTextField titleField;
        private JLabel tree1Label;
        private JTextField tree1Field;
        private JLabel keyLabel;
        private JTextField keyField;
        private JLabel prim2Label;
        private JTextField prim2Field;
        private JLabel prim3Label;
        private JTextField prim3Field;
        private JLabel prim4Label;
        private JTextField prim4Field;
        private JLabel tree2Label;
        private JTextField tree2Field;
        private JLabel sec1Label;
        private JTextField sec1Field;
        private JLabel sec2Label;
        private JTextField sec2Field;

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            addFrame = new JFrame();
            addFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            addFrame.setTitle("Add Rune Page");
            addFrame.setSize(250, 500);
            setupAddScreen();
            setupPanel();
            addFrame.setVisible(true);
            BackButtonListener backButtonListener = new BackButtonListener();
            back.addActionListener(backButtonListener);
        }

        //MODIFIES: this
        //EFFECTS: adds all components to panel
        private void setupPanel() {
            JPanel panel = new JPanel();
            JPanel nextPanel = new JPanel();
            panel.add(titleLabel);
            panel.add(titleField);
            panel.add(tree1Label);
            panel.add(tree1Field);
            panel.add(keyLabel);
            panel.add(keyField);
            panel.add(prim2Label);
            panel.add(prim2Field);
            panel.add(prim3Label);
            panel.add(prim3Field);
            panel.add(prim4Label);
            panel.add(prim4Field);
            panel.add(tree2Label);
            panel.add(tree2Field);
            panel.add(sec1Label);
            panel.add(sec1Field);
            panel.add(sec2Label);
            panel.add(sec2Field);
            nextPanel.add(back);
            addFrame.add(panel);
            addFrame.add(nextPanel, BorderLayout.SOUTH);
        }

        //MODIFIES: this
        //EFFECTS: sets up all components of this frame
        private void setupAddScreen() {
            back = new JButton("save");

            titleLabel = new JLabel("Title: ");
            titleField = new JTextField(15);
            tree1Label = new JLabel("Primary tree: ");
            tree1Field = new JTextField(15);
            keyLabel = new JLabel("Keystone: ");
            keyField = new JTextField(15);
            prim2Label = new JLabel("Primary 2: ");
            prim2Field = new JTextField(15);
            prim3Label = new JLabel("Primary 3: ");
            prim3Field = new JTextField(15);
            prim4Label = new JLabel("Primary 4: ");
            prim4Field = new JTextField(15);
            tree2Label = new JLabel("Secondary tree: ");
            tree2Field = new JTextField(15);
            sec1Label = new JLabel("Secondary 1: ");
            sec1Field = new JTextField(15);
            sec2Label = new JLabel("Secondary 2: ");
            sec2Field = new JTextField(15);
        }

        private class BackButtonListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent event) {
                Rune rune = new Rune(titleField.getText(), tree1Field.getText(), tree2Field.getText(),
                        keyField.getText(), prim2Field.getText(), prim3Field.getText(), prim4Field.getText(),
                        sec1Field.getText(), sec2Field.getText());
                runeList.addRune(rune);
                addFrame.setVisible(false);
                runApp();
            }
        }
    }
}
