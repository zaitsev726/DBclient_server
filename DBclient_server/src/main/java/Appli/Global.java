package Appli;


import Appli.Entities.AllReader;
import Appli.Entities.Characteristic;
import Appli.Entities.Librarian;
import Appli.Entities.Library;
import Appli.Entities.Types.Pensioner;
import Appli.Services.Impl.AllReaderServiceImpl;
import Appli.Services.Impl.CharacteristicServiceImpl;
import Appli.Services.Impl.LibrarianServiceImpl;
import Appli.Services.Impl.LibraryServiceImpl;
import Appli.UserInterface.Frames.Library.SearchReadersInLibraryForm;
import Appli.UserInterface.Frames.SearchReadersForm;
import Appli.UserInterface.InterfaceController;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Global {
    private InterfaceController interfaceController;

    public Global(){
       // interfaceController = new InterfaceController();
        AllReaderServiceImpl a = new AllReaderServiceImpl();
        LibraryServiceImpl b = new LibraryServiceImpl();
        LibrarianServiceImpl c = new LibrarianServiceImpl();


        CharacteristicServiceImpl d = new CharacteristicServiceImpl();

       // AllReader reader = a.findById((long) 1);
        //a.delete(reader);
        //d.delete((long) 1);
        Characteristic characteristic = d.findById((long) 2);
        System.out.println(characteristic.getEdition().getBook_library());

        System.out.println(b.getById((long) 3).getEditions());
      //  Characteristic characteristic = new Characteristic();
       // characteristic.setType_edition("type");
        //characteristic.setTitle("title");
        //characteristic.setAuthor("author");
       // System.out.println(characteristic.getInformation());
    }
}
