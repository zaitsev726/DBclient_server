package Application.Controllers;

import Application.Services.*;
import Application.Services.Impl.*;
import Application.UserInterface.Pages.EditionPage.EditionForm;
import Application.UserInterface.Pages.IssuedPage.*;
import Application.UserInterface.Pages.LibraryPage.LibraryForm;
import Application.UserInterface.Pages.MenuPage.MenuForm;
import Application.UserInterface.Pages.ReadersPage.ReadersForm;
import Application.UserInterface.Pages.TakeBookPage.AuthorizationForm;
import Application.UserInterface.Pages.TakeBookPage.TakeBookForm;

import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.swing.*;
import java.awt.*;
import java.io.Reader;

public class InterfaceController {
    private final int sizeWidth = 800;
    private final int sizeHeight = 600;
    private final int locationX = (1920 - sizeWidth) / 2;
    private final int locationY = (1080 - sizeHeight) / 2 - 20;
    private final Window window;
    private final MenuForm menuForm;


    private final ReadersPageController readersPageController;
    private final ReadersForm readersForm = new ReadersForm();

    private final LibraryPageController libraryPageController;
    private final LibraryForm libraryForm = new LibraryForm();

    private final EditionsPageController editionsPageController;
    private final EditionForm editionForm = new EditionForm();


    private final IssuedPageController issuedPageController;
    private final IssuedForm issuedForm = new IssuedForm();
    private final EditionSearchForm editionSearchForm = new EditionSearchForm();

    private final TakeBookPageController takeBookPageController;
    private final AuthorizationForm authorizationForm = new AuthorizationForm();
    private final TakeBookForm takeBookForm = new TakeBookForm();
    private final LibrarianSearchForm librarianSearchForm = new LibrarianSearchForm();
    private final NotAttendingForm notAttendingForm = new NotAttendingForm();
    private final InformationSearchForm informationSearchForm = new InformationSearchForm();

    //Все сервисы
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("model");
    private final AbstractReaderService abstractReaderService = new AbstractReaderServiceImpl(emf);
    private final AllReaderService readerService = new AllReaderServiceImpl(emf);
    private final CharacteristicService characteristicService = new CharacteristicServiceImpl(emf);
    private final EditionService editionService = new EditionServiceImpl(emf);
    private final InformationService informationService = new InformationServiceImpl(emf);
    private final IssuedBookService issuedBookService = new IssuedBookServiceImpl(emf);
    private final LibrarianService librarianService = new LibrarianServiceImpl(emf);
    private final LibraryService libraryService = new LibraryServiceImpl(emf);
    private final RuleService ruleService = new RuleServiceImpl(emf);

    public InterfaceController() {
        window = new Application.UserInterface.Frames.Window(sizeWidth, sizeHeight, locationX, locationY);

        menuForm = new MenuForm();

        readersPageController = new ReadersPageController(readersForm, abstractReaderService, readerService, libraryService);
        libraryPageController = new LibraryPageController(libraryForm, libraryService, librarianService);
        editionsPageController = new EditionsPageController(editionForm, characteristicService, editionService, informationService,
                libraryService, ruleService, issuedBookService);
        issuedPageController = new IssuedPageController(issuedForm, editionSearchForm, librarianSearchForm, notAttendingForm,
                issuedBookService, librarianService, editionService, readerService, informationService, informationSearchForm);
        takeBookPageController = new TakeBookPageController(authorizationForm, takeBookForm, readerService, informationService,
                issuedBookService, characteristicService);

        initializationListeners();
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

        issuedForm.librarianButton.addActionListener(e -> {
            try {
                if (issuedPageController.checkLibrarian()) {

                    takeBookPageController.setReader(authorizationForm.id_reader);
                    window.remove(issuedForm);
                    window.add(librarianSearchForm);
                    window.revalidate();
                    window.repaint();
                } else {
                    JOptionPane.showMessageDialog(window, "Вы не ввели ID работника");
                }
            } catch (NoResultException ignored) {
                JOptionPane.showMessageDialog(window, "Такого работника библиотеки не существует");
            }
        });

        librarianSearchForm.backButton.addActionListener(e -> {
            window.remove(librarianSearchForm);
            window.add(issuedForm);
            window.revalidate();
            window.repaint();
        });

        issuedForm.notAttendingButton.addActionListener(e -> {
            window.remove(issuedForm);
            window.add(notAttendingForm);
            window.revalidate();
            window.repaint();
        });

        notAttendingForm.backButton.addActionListener(e -> {
            window.remove(notAttendingForm);
            window.add(issuedForm);
            window.revalidate();
            window.repaint();
        });

        informationSearchForm.backButton.addActionListener(e -> {
            window.remove(informationSearchForm);
            window.add(issuedForm);
            window.revalidate();
            window.repaint();
        });

        issuedForm.titleSearchButton.addActionListener(e -> {
            window.remove(issuedForm);
            window.add(informationSearchForm);
            window.revalidate();
            window.repaint();
        });
    }


}
