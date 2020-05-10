package Appli.Controllers;

import Appli.UserInterface.Pages.Library.LibraryForm;

public class LibraryPageController {

    private LibraryForm libraryForm;

    private long cur_libID;
    private long cur_hallNum;

    public LibraryPageController(LibraryForm form){
        libraryForm = form;

        setStartValues();
    }

    private void setStartValues(){
        cur_libID = 0;
        cur_hallNum = 0;
    }



}
