package Appli.UserInterface;

import Appli.Controllers.LibraryPageController;
import Appli.Controllers.ReadersPageController;
import Appli.UserInterface.Pages.LibraryPage.LibraryForm;
import Appli.UserInterface.Pages.MenuPage.MenuForm;
import Appli.UserInterface.Pages.MenuPage.MenuPanel;
import Appli.UserInterface.Pages.ReadersPage.ReadersForm;

import java.awt.*;

public class InterfaceController {
    //private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int sizeWidth = 800;
    private int sizeHeight = 600;
    private int locationX = (1920 - sizeWidth) / 2;
    private int locationY = (1080 - sizeHeight) / 2 - 20;
    private Window window;
  //  private MenuPanel menuPanel;
    private MenuForm menuForm;

    private ReadersPageController readersPageController;
    private ReadersForm readersForm;

    private LibraryForm libraryForm;
    private LibraryPageController libraryPageController;

    public InterfaceController(){
        window = new Appli.UserInterface.Frames.Window(sizeWidth,sizeHeight,locationX,locationY);

    //    menuPanel = new MenuPanel(sizeWidth, sizeHeight);
        menuForm = new MenuForm(sizeWidth,sizeHeight);

        readersForm = new ReadersForm();
        libraryForm = new LibraryForm();

        readersPageController = new ReadersPageController(readersForm);
        libraryPageController = new LibraryPageController(libraryForm);


        initializationListeners();

       // window.add(menuPanel);
        window.add(menuForm);
        window.setVisible(true);
    }

    private void initializationListeners(){ initializationMenuListeners(); }

    private void initializationMenuListeners() {

        menuForm.readersButton.addActionListener(e -> {
            window.remove(menuForm);
            window.add(readersForm);
            window.revalidate();
            window.repaint();
        });

        menuForm.librariesButton.addActionListener(e -> {
            window.remove(menuForm);
            window.add(libraryForm);
            window.revalidate();
            window.repaint();
        });

        readersForm.backButton.addActionListener(e -> {
            window.remove(readersForm);
            window.add(menuForm);
            window.revalidate();
            window.repaint();
        });

        libraryForm.backButton.addActionListener(e -> {
            window.remove(libraryForm);
            window.add(menuForm);
            window.revalidate();
            window.repaint();
        });

    }


}
