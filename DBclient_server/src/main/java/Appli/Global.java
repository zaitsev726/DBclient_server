package Appli;


import Appli.Entities.AllReader;
import Appli.Entities.Librarian;
import Appli.Entities.Library;
import Appli.Entities.Types.Pensioner;
import Appli.Services.Impl.AllReaderServiceImpl;
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
        interfaceController = new InterfaceController();
        AllReaderServiceImpl a = new AllReaderServiceImpl();
        LibraryServiceImpl b = new LibraryServiceImpl();
        LibrarianServiceImpl c = new LibrarianServiceImpl();
        Library library = new Library();
        //  b.delete((long) 7);
        library.setId_library((long) 7);
        library.setHalls_num((long) 3);
        //   b.save(library);

     //   c.delete(40);
      //  Librarian librarian = new Librarian();
       // librarian.setId_librarian((long) 40);
     //   librarian.setId_library((long) 7);
      //  librarian.setHall_num((long) 3);
      //  c.save(librarian);


    }
}
