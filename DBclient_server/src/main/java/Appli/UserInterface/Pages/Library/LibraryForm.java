package Appli.UserInterface.Pages.Library;

import javax.swing.*;

public class LibraryForm extends JPanel {
    private JLabel libraryJLabel;
    public JTextField IDTextField;
    public JTextField hallNumTextField;
    private JButton backButton;
    private JLabel idLabel;
    private JLabel hallLabel;

    public JButton addButton;
    public JButton searchButton;
    public JButton editionButton;
    public JButton librariansButton;
    public JButton readerSearchButton;

    private JPanel libraryPanel;

    public LibraryForm(){
        this.add(libraryPanel);
        setVisible(true);
    }
}
