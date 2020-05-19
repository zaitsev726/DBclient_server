package Appli;


import Appli.Entities.*;
import Appli.Entities.Types.Pensioner;
import Appli.Services.Impl.*;
import Appli.Services.InformationService;
import Appli.Services.IssuedBookService;
import Appli.UserInterface.Frames.Library.SearchReadersInLibraryForm;
import Appli.UserInterface.Frames.SearchReadersForm;
import Appli.UserInterface.InterfaceController;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Global {
    private InterfaceController interfaceController;

    public Global(){
       // interfaceController = new InterfaceController();
        AllReaderServiceImpl a = new AllReaderServiceImpl();
        LibraryServiceImpl b = new LibraryServiceImpl();
        LibrarianServiceImpl c = new LibrarianServiceImpl();


        CharacteristicServiceImpl d = new CharacteristicServiceImpl();
        EditionServiceImpl e = new EditionServiceImpl();
        IssuedBookService i = new IssuedBookServiceImpl();
       // AllReader reader = a.findById((long) 1);
        //a.delete(reader);
        //d.delete((long) 1);
       // Characteristic characteristic = d.findById((long) 2);
       // System.out.println(characteristic.getEdition().getBook_library());

        //System.out.println(b.getById((long) 3).getEditions());

       // edition.setId_edition(characteristic.getId_edition());

    /*    Edition edition = new Edition();
        edition.setId_edition((long) 60);
        edition.setId_library((long) 2);
        edition.setHall_num(1);
        edition.setRack_num(1);
        edition.setShelf_num(2);
        edition.setDate_adding(new Date());
        edition.setDate_removing(null);
       // edition.setBook_library(b.getById((long) 2));
        Characteristic characteristic = new Characteristic();

        characteristic.setType_edition("type");
        characteristic.setTitle("title");
        characteristic.setAuthor("author");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("model");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
       // Edition edition = em.createQuery("select ed from Edition ed where ed.id_edition = :id", Edition.class)
       //         .setParameter("id", (long) 2)
        //        .getSingleResult();
       // em.merge(edition);
        characteristic = em.merge(characteristic);
        edition.setId_edition(characteristic.getId_edition());
        edition = em.merge(edition);
        System.out.println(edition.getCharacteristic());
       // edition.setCharacteristic(characteristic);
      //  characteristic.setEdition(edition);

        edition = em.createQuery("select  ed from Edition ed where ed.id_edition = :id", Edition.class)
                .setParameter("id", (long) 33)
                .getSingleResult();
        System.out.println("***********************************" +  edition.getRules());

        em.getTransaction().commit();
        // System.out.println(edition);
        em.clear();
       /* Edition edition1 = em.createQuery("select ed from Edition ed where ed.id_edition = :id", Edition.class)
                .setParameter("id", characteristic.getId_edition())
                .getSingleResult();

        System.out.println(edition.getRules());
        */
      //  RuleServiceImpl r = new RuleServiceImpl();
      //  r.delete((long) 1);

        //String str = "12.31.1999";

       // System.out.println(date);
       // System.out.println(date);
       // System.out.println(e.findByMoreDateAdding(date));
        System.out.println(i.findReadersWithType("Роман"));
    }
}
