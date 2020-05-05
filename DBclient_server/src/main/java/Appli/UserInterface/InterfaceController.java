package Appli.UserInterface;

import Appli.Controllers.ReadersPageController;
import Appli.UserInterface.Pages.MenuPage.MenuPanel;
import Appli.UserInterface.Pages.ReadersPage.ReadersForm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceController {
    //private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int sizeWidth = 800;
    private int sizeHeight = 600;
    private int locationX = (1920 - sizeWidth) / 2;
    private int locationY = (1080 - sizeHeight) / 2 - 20;
    private Window window;
    private MenuPanel menuPanel;


    private ReadersPageController readersPageController;

    private ReadersForm form;

    public InterfaceController(){
        window = new Appli.UserInterface.Frames.Window(sizeWidth,sizeHeight,locationX,locationY);

        menuPanel = new MenuPanel(sizeWidth, sizeHeight);
        
        form = new ReadersForm();
        readersPageController = new ReadersPageController(form);


        initializationListeners();

        window.add(menuPanel);
        window.setVisible(true);
    }

    private void initializationListeners(){ initializationMenuListeners(); }

    private void initializationMenuListeners() {

        menuPanel.readersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                window.remove(menuPanel);
                window.add(form);
                window.revalidate();
                window.repaint();
            }
        });
    }


}
