package Appli.UserInterface.Pages.Library;

import javax.swing.*;

public class LibraryForm extends JPanel {
    private JLabel libraryJLabel;
    private JTextField IDTextField;
    private JButton addButton;
    private JButton searchButton;
    private JButton readerSearchButton;
    private JTextField hallNumTextField;
    private JButton backButton;
    private JLabel idLabel;
    private JLabel hallLabel;
    private JButton editionButton;
    private JButton librarianButton;
    private JPanel libraryPanel;

    public LibraryForm(){
        this.add(libraryPanel);
        setVisible(true);
    }
}
