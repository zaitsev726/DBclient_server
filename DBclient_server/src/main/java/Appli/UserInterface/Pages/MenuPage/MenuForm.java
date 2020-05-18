package Appli.UserInterface.Pages.MenuPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.System.exit;

public class MenuForm extends JPanel {

    private JPanel menuPanel;
    public JButton readersButton;
    public JButton librariesButton;
    public JButton editionsButton;
    private JButton exitButton;
    private JButton issuedButton;

    public MenuForm(int sizeWidth, int sizeHeight) {
        this.add(menuPanel);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exit(0);
            }
        });

        setVisible(true);

    }

}
