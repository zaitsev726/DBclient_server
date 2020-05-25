package Application.UserInterface.Pages.MenuPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.System.exit;

public class MenuPanel extends JPanel {
    private final int buttonWidth = 300;
    private final int buttonHeight = 100;

    public JButton readersButton;
    public JButton librariesButton;
    public JButton netInfoButton;
    public JButton editionsButton;
    private JButton exitButton;

    public MenuPanel(int sizeWidth, int sizeHeight) {
        this.setLayout(null);

        readersButton = new JButton("Читатели");
        librariesButton = new JButton("Библиотеки");
        editionsButton = new JButton("Издания");
        netInfoButton = new JButton("12312312312321123");
        exitButton = new JButton("EXIT");

        readersButton.setVerticalTextPosition(AbstractButton.CENTER);
        readersButton.setHorizontalTextPosition(AbstractButton.CENTER);

        librariesButton.setVerticalTextPosition(AbstractButton.CENTER);
        librariesButton.setHorizontalTextPosition(AbstractButton.CENTER);

        editionsButton.setVerticalTextPosition(AbstractButton.CENTER);
        editionsButton.setHorizontalTextPosition(AbstractButton.CENTER);

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

        readersButton.setBounds((sizeWidth - buttonWidth) / 2, (sizeHeight-buttonWidth)/2 - 5 - 2*buttonHeight - 5, buttonWidth, buttonHeight);
        librariesButton.setBounds((sizeWidth - buttonWidth) / 2, (sizeHeight/2) - 5 - buttonWidth, buttonWidth, buttonHeight);
        editionsButton.setBounds((sizeWidth - buttonWidth) / 2, (sizeHeight/2) + 5 + buttonWidth, buttonWidth,buttonHeight);
        exitButton.setBounds((sizeWidth - buttonWidth) / 2, (sizeHeight-buttonWidth)/2 + 5 + 2*buttonHeight + 5, buttonWidth, buttonHeight);

        this.add(readersButton);
        this.add(librariesButton);
        this.add(editionsButton);
        this.add(exitButton);
        this.add(netInfoButton);

        setVisible(true);
    }
}
