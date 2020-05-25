package Application.UserInterface.Pages.TakeBookPage;

import javax.swing.*;

public class TakeBookForm extends JPanel {


    private JLabel Jlabel;
    private JScrollPane firstPane;
    private JPanel takeBookPanel;
    public JButton myInfoButton;
    public JButton takeNewButton;
    public JButton myEditionsButton;
    public JButton backButton;

    public TakeBookForm(){

        this.add(takeBookPanel);
        setVisible(true);
    }

}
