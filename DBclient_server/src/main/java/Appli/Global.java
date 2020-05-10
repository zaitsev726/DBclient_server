package Appli;


import Appli.Entities.AllReader;
import Appli.Entities.Library;
import Appli.Entities.Types.Pensioner;
import Appli.Services.Impl.AllReaderServiceImpl;
import Appli.Services.Impl.LibraryServiceImpl;
import Appli.UserInterface.Frames.SearchReadersForm;
import Appli.UserInterface.InterfaceController;
import com.sun.org.apache.bcel.internal.generic.LUSHR;



public class Global {
    private InterfaceController interfaceController;

    public Global(){
        interfaceController = new InterfaceController();
        AllReaderServiceImpl a = new AllReaderServiceImpl();
        LibraryServiceImpl b = new LibraryServiceImpl();
        Library library = new Library();
        library.setId_library((long) 7);
        library.setHalls_num((long) 3);
        //b.save(library);
       // b.delete((long) 1);
    }
}
