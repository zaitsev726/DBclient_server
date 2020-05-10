package Appli;


import Appli.Entities.AllReader;
import Appli.Entities.Library;
import Appli.Entities.Types.Pensioner;
import Appli.Services.Impl.AllReaderServiceImpl;
import Appli.Services.Impl.LibraryServiceImpl;
import Appli.UserInterface.Frames.SearchReadersForm;
import Appli.UserInterface.InterfaceController;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class Global {
    private InterfaceController interfaceController;

    public Global(){
        interfaceController = new InterfaceController();
        AllReaderServiceImpl a = new AllReaderServiceImpl();
        LibraryServiceImpl b = new LibraryServiceImpl();
       // List<AllReader> readers = a.findAll();
        AllReader reader = new AllReader();
       // reader.setId_library((long) 0);

        reader.setName("Константин");
        reader.setSurname("Агафонцауцуов");
        reader.setPatronymic("Федосеевич");
        reader.setType("pensioner");
        reader.setId_library((long) 1);
       // a.delete(reader);

       // SearchReadersForm form = new SearchReadersForm();
        ArrayList<String[]> list = new ArrayList<>();
       // reader = a.findById((long) 1);
       //form.updateTable(list);
       // System.out.println(reader.getReaderType());

        //  reader.setId_reader((long) 0);
       // reader.setLibrary(b.getById((long) 1));
        //reader.setLibrary(null);
        Pensioner pensioner = new Pensioner();
        pensioner.setId_pensioners((long) 123213);
        pensioner.setType("pensioner");

        //Library library = b.getById((long) 1);


      //  reader = a.save(reader);
     //   System.out.println(reader);
      //  reader.getReaderType().setId_reader(reader.getId_reader());

   /*     reader = a.findById((long) 13);
        System.out.println(reader);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("model");
        EntityManager manager = emf.createEntityManager();

        Query query = manager.createQuery("delete from Pensioner p where p.id_reader = :r_id_reader");
        query.setParameter("r_id_reader", reader.getId_reader());

      //  Pensioner pensioner = manager.find(Pensioner.class,(long) 0);
       // reader.setReaderType(pensioner);


      //  pensioner1.setId_reader( reader.getId_reader());
      //  System.out.println(pensioner1);
        manager.getTransaction().begin();
      //  manager.merge(pensioner1);
     //   reader.setReaderType(pensioner1);
        manager.merge(reader);

        manager.getTransaction().commit();
      //  System.out.println(pensioner1);
        System.out.println(reader);

        System.out.println(reader.getReaderType());



        a.delete(reader);
       // library = b.getById((long) 1);
       // System.out.println(a.findById((long) 1).getLibrary());
        //library = b.getById((long) 1);
      //  System.out.println(library.getReaders());

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
