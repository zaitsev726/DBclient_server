package Appli.UserInterface;

import Appli.Controllers.EditionsPageController;
import Appli.Controllers.IssuedPageController;
import Appli.Controllers.LibraryPageController;
import Appli.Controllers.ReadersPageController;
import Appli.UserInterface.Pages.EditionPage.EditionForm;
import Appli.UserInterface.Pages.IssuedPage.EditionSearchForm;
import Appli.UserInterface.Pages.IssuedPage.IssuedForm;
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

    private LibraryPageController libraryPageController;
    private LibraryForm libraryForm;

    private EditionsPageController editionsPageController;
    private EditionForm editionForm;


    private IssuedPageController issuedPageController;
    private IssuedForm issuedForm;
    private EditionSearchForm editionSearchForm;


    public InterfaceController(){
        window = new Appli.UserInterface.Frames.Window(sizeWidth,sizeHeight,locationX,locationY);

    //    menuPanel = new MenuPanel(sizeWidth, sizeHeight);
        menuForm = new MenuForm(sizeWidth,sizeHeight);

        readersForm = new ReadersForm();
        libraryForm = new LibraryForm();
        editionForm = new EditionForm();
        issuedForm = new IssuedForm();
        editionSearchForm = new EditionSearchForm();

        readersPageController = new ReadersPageController(readersForm);
        libraryPageController = new LibraryPageController(libraryForm);
        editionsPageController = new EditionsPageController(editionForm);
        issuedPageController = new IssuedPageController(issuedForm, editionSearchForm);

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

        menuForm.editionsButton.addActionListener(e -> {
            window.remove(menuForm);
            window.add(editionForm);
            window.revalidate();
            window.repaint();
        });

        menuForm.issuedButton.addActionListener(e -> {
            window.remove(menuForm);
            window.add(issuedForm);
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

        editionSearchForm.backButton.addActionListener(e -> {
            window.remove(editionSearchForm);
            window.add(issuedForm);
            window.repaint();
            window.revalidate();
        });

        issuedForm.searchByEditionButton.addActionListener(e ->{
            window.remove(issuedForm);
            window.add(editionSearchForm);
            window.revalidate();
            window.repaint();
        });
        issuedForm.backButton.addActionListener(e -> {
            window.remove(issuedForm);
            window.add(menuForm);
            window.revalidate();
            window.repaint();
        });

    }


}
