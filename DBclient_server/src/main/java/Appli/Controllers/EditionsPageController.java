package Appli.Controllers;

import Appli.UserInterface.Pages.EditionPage.EditionForm;

import javax.swing.*;

public class EditionsPageController {
    private EditionForm editionForm;
    private long cur_IdEdition;
    private String cur_type;
    private String cur_author;
    private String cur_title;


    public EditionsPageController(EditionForm editionForm){
        this.editionForm = editionForm;

        initializationListeners();
        setStartValues();
    }

    private void setStartValues(){
        cur_IdEdition = 0;
        cur_type = "";
        cur_author = "";
        cur_title = "";
    }

    private void initializationListeners() {

    }
}
