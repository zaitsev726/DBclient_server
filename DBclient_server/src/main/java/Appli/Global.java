package Appli;


import Appli.Entities.AllReader;
import Appli.Entities.Library;
import Appli.Entities.Types.Pensioner;
import Appli.Services.Impl.AllReaderServiceImpl;
import Appli.Services.Impl.LibraryServiceImpl;
import Appli.UserInterface.InterfaceController;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class Global {
    private InterfaceController interfaceController;

    public Global(){
       // interfaceController = new InterfaceController();
        AllReaderServiceImpl a = new AllReaderServiceImpl();
        LibraryServiceImpl b = new LibraryServiceImpl();
       // List<AllReader> readers = a.findAll();
        AllReader reader = new AllReader();
        reader.setId_library((long) 1);
        reader.setName("Иван");
        reader.setSurname("Иванов");
        reader.setPatronymic("Иванович");
        reader.setType("none");
        reader.setLibrary(b.getById((long) 1));
        Pensioner pensioner = new Pensioner();
        pensioner.setId_pensioners((long) 123213);
        pensioner.setType("pensioner");
        //reader.setReaderType(pensioner);

        Library library = b.getById((long) 1);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("model");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        System.out.println(library.getReaders());
        em.getTransaction().commit();

        System.out.println(a.save(reader));

     //   library = b.getById((long) 1);
     //   System.out.println(library.getReaders());

        /* AllReader reader1 = a.findById((long) 1);
        AllReader reader2 = a.findById((long) 11);
        AllReader reader3 = a.findById((long) 21);
        AllReader reader4 = a.findById((long) 31);
        AllReader reader5 = a.findById((long) 41);
        AllReader reader6 = a.findById((long) 51);
        System.out.println(reader1);
        System.out.println(reader1.getLibrary());
        System.out.println(reader1.getReaderType());

        System.out.println(reader2);
        System.out.println(reader2.getLibrary());
        System.out.println(reader2.getReaderType());

        System.out.println(reader3);
        System.out.println(reader3.getLibrary());
        System.out.println(reader3.getReaderType());

        System.out.println(reader3);
        System.out.println(reader3.getLibrary());
        System.out.println(reader3.getReaderType());

        System.out.println(reader4);
        System.out.println(reader4.getLibrary());
        System.out.println(reader4.getReaderType());

        System.out.println(reader5);
        System.out.println(reader5.getLibrary());
        System.out.println(reader5.getReaderType());

        System.out.println(reader6);
        System.out.println(reader6.getLibrary());
        System.out.println(reader6.getReaderType());*/
    }
}
