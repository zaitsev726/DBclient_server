package Appli.UserInterface.Pages.MenuPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.System.exit;

public class MenuPanel extends JPanel {
    private final int buttonWidth = 1000;
    private final int buttonHeight = 150;

    public JButton readersButton;
    public JButton newGameButton;
    public JButton netInfoButton;
    private JButton exitButton;

    public MenuPanel(int sizeWidth, int sizeHeight) {
        this.setLayout(null);

        readersButton = new JButton("READERS");
        newGameButton = new JButton("N");
        netInfoButton = new JButton("N");
        exitButton = new JButton("EXIT");

        readersButton.setVerticalTextPosition(AbstractButton.CENTER);
        readersButton.setHorizontalTextPosition(AbstractButton.CENTER);

        newGameButton.setVerticalTextPosition(AbstractButton.CENTER);
        newGameButton.setHorizontalTextPosition(AbstractButton.CENTER);

        netInfoButton.setVerticalTextPosition(AbstractButton.CENTER);
        netInfoButton.setHorizontalTextPosition(AbstractButton.CENTER);

        exitButton.setVerticalTextPosition(AbstractButton.CENTER);
        exitButton.setHorizontalTextPosition(AbstractButton.CENTER);

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exit(0);
            }
        });

        netInfoButton.setBounds(10,10,100,20);

        readersButton.setBounds((sizeWidth - buttonWidth) / 2, (sizeHeight - buttonHeight) / 2 - buttonHeight - 5, buttonWidth, buttonHeight);
        newGameButton.setBounds((sizeWidth - buttonWidth) / 2, (sizeHeight - buttonHeight) / 2, buttonWidth, buttonHeight);
        exitButton.setBounds((sizeWidth - buttonWidth) / 2, (sizeHeight - buttonHeight) / 2 + buttonHeight + 5, buttonWidth, buttonHeight);

        this.add(readersButton);
        this.add(newGameButton);
        this.add(exitButton);
        this.add(netInfoButton);

        setVisible(true);
    }
}
