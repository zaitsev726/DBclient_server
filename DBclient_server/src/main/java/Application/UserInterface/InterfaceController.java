package Application.UserInterface;

import Application.Controllers.*;
import Application.UserInterface.Pages.EditionPage.EditionForm;
import Application.UserInterface.Pages.IssuedPage.EditionSearchForm;
import Application.UserInterface.Pages.IssuedPage.IssuedForm;
import Application.UserInterface.Pages.LibraryPage.LibraryForm;
import Application.UserInterface.Pages.MenuPage.MenuForm;
import Application.UserInterface.Pages.ReadersPage.ReadersForm;
import Application.UserInterface.Pages.TakeBookPage.AuthorizationForm;
import Application.UserInterface.Pages.TakeBookPage.TakeBookForm;

import javax.persistence.NoResultException;
import javax.swing.*;
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

    private TakeBookPageController takeBookPageController;
    private AuthorizationForm authorizationForm;
    private TakeBookForm takeBookForm;

    public InterfaceController() {
        window = new Application.UserInterface.Frames.Window(sizeWidth, sizeHeight, locationX, locationY);

        //    menuPanel = new MenuPanel(sizeWidth, sizeHeight);
        menuForm = new MenuForm(sizeWidth, sizeHeight);

        readersForm = new ReadersForm();
        libraryForm = new LibraryForm();
        editionForm = new EditionForm();
        issuedForm = new IssuedForm();
        editionSearchForm = new EditionSearchForm();
        authorizationForm = new AuthorizationForm();
        takeBookForm = new TakeBookForm();


        readersPageController = new ReadersPageController(readersForm);
        libraryPageController = new LibraryPageController(libraryForm);
        editionsPageController = new EditionsPageController(editionForm);
        issuedPageController = new IssuedPageController(issuedForm, editionSearchForm);
        takeBookPageController = new TakeBookPageController(authorizationForm, takeBookForm);

        initializationListeners();

        // window.add(menuPanel);
        window.add(menuForm);
        window.setVisible(true);
    }

    private void initializationListeners() {
        initializationMenuListeners();
    }

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

        menuForm.myBooksButton.addActionListener(e -> {
            window.remove(menuForm);
            window.add(authorizationForm);
            window.revalidate();
            window.repaint();
        });

        authorizationForm.backButton.addActionListener(e -> {
            window.remove(authorizationForm);
            window.add(menuForm);
            window.revalidate();
            window.repaint();
        });

        authorizationForm.continueButton.addActionListener(e -> {
            if (authorizationForm.id_reader != 0) {
                try {
                    takeBookPageController.setReader(authorizationForm.id_reader);
                    window.remove(authorizationForm);
                    window.add(takeBookForm);
                    window.revalidate();
                    window.repaint();
                } catch (NoResultException ignored) {
                    JOptionPane.showMessageDialog(window, "Такого читателя не существует");
                }
            } else {
                JOptionPane.showMessageDialog(window, "Вы не ввели ID");
            }
        });

        takeBookForm.backButton.addActionListener(e -> {
            window.remove(takeBookForm);
            window.add(menuForm);
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

        editionForm.backButton.addActionListener(e -> {
            window.remove(editionForm);
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

        issuedForm.searchByEditionButton.addActionListener(e -> {
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
