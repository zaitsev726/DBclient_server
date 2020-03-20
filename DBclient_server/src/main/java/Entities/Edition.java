package Entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "editions")
public class Edition {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_edition")
    private int id_edition;         //номер книги в библиотеке (в сети библиотек)

    @Column(name = "id_library")
    private int id_library;         //номер библиотеки

    @Column(name = "hall_num")
    private int hall_num;           //номер зала

    @Column(name = "rack_num")
    private int rack_num;           //номер стеллажа

    @Column(name = "shelf_num")
    private int shelf_num;          //номер полки

    @Column(name = "date_adding")
    private Date date_adding;       //дата добавления

    @Column(name = "date_removing")
    private Date date_removing;     //дата удаления

    public Edition(int id_edition, int id_library, int hall_num, int rack_num, int shelf_num, Date date_adding) {
        this.id_edition = id_edition;
        this.id_library = id_library;
        this.hall_num = hall_num;
        this.rack_num = rack_num;
        this.shelf_num = shelf_num;
        this.date_adding = date_adding;
        this.date_removing = null;
    }

    public Edition(){}
    
    public int getId_edition() {
        return id_edition;
    }

    public void setId_edition(int id_edition) {
        this.id_edition = id_edition;
    }

    public int getId_library() {
        return id_library;
    }

    public void setId_library(int id_library) {
        this.id_library = id_library;
    }

    public int getHall_num() {
        return hall_num;
    }

    public void setHall_num(int hall_num) {
        this.hall_num = hall_num;
    }

    public int getRack_num() {
        return rack_num;
    }

    public void setRack_num(int rack_num) {
        this.rack_num = rack_num;
    }

    public int getShelf_num() {
        return shelf_num;
    }

    public void setShelf_num(int shelf_num) {
        this.shelf_num = shelf_num;
    }

    public Date getDate_adding() {
        return date_adding;
    }

    public void setDate_adding(Date date_adding) {
        this.date_adding = date_adding;
    }

    public Date getDate_removing() {
        return date_removing;
    }

    public void setDate_removing(Date date_removing) {
        this.date_removing = date_removing;
    }

    @Override
    public String toString() {
            String re = "Edition{ " +
                    "id_edition = " + id_edition + ' ' +
                    "id_library = " + id_library + ' ' +
                    "hall_num = " + hall_num + ' ' +
                    "rack_num = " + rack_num + ' ' +
                    "shelf_num = " + shelf_num + ' ' +
                    "date_adding = " + date_adding + ' '+
                    "date_removing = ";

             re += date_removing == null?"такой даты нет" + ' ' + '}':date_removing.toString() + ' ' + '}';
             return  re;
    }
}