package Appli.UserInterface;

import Appli.Controllers.ReadersPageController;
import Appli.UserInterface.Pages.Library.LibraryForm;
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
    private ReadersForm readersForm;

    private LibraryForm libraryForm;

    public InterfaceController(){
        window = new Appli.UserInterface.Frames.Window(sizeWidth,sizeHeight,locationX,locationY);

        menuPanel = new MenuPanel(sizeWidth, sizeHeight);

        readersForm = new ReadersForm();
        libraryForm = new LibraryForm();
        readersPageController = new ReadersPageController(readersForm);


        initializationListeners();

        window.add(menuPanel);
        window.setVisible(true);
    }

    private void initializationListeners(){ initializationMenuListeners(); }

    private void initializationMenuListeners() {

        menuPanel.readersButton.addActionListener(e -> {
            window.remove(menuPanel);
            window.add(readersForm);
            window.revalidate();
            window.repaint();
        });

        menuPanel.librariesButton.addActionListener(e -> {
            window.remove(menuPanel);
            window.add(libraryForm);
            window.revalidate();
            window.repaint();
        });

        readersForm.backButton.addActionListener(e -> {
            window.remove(readersForm);
            window.add(menuPanel);
            window.revalidate();
            window.repaint();
        });


    }


}
