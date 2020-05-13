package Appli.Entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table (name = "editions")
public class Edition {
    @Id
    @Column(name = "id_edition")
    private Long id_edition;         //номер книги в библиотеке (в сети библиотек)

    @Column(name = "id_library")
    private Long id_library;         //номер библиотеки

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



    @OneToOne(optional = true, mappedBy = "edition")
    private Characteristic characteristic;

    public Characteristic getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(Characteristic characteristic) {
        this.characteristic = characteristic;
    }

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_library", referencedColumnName = "id_library", updatable = false, insertable = false)
    private Library book_library;

    public Library getBook_library() {
        return book_library;
    }

    public void setBook_library(Library book_library) {
        this.book_library = book_library;
    }


    /*   @OneToMany(mappedBy = "edition", fetch = FetchType.LAZY)
            private Collection<IssuedBook> records;

            @OneToMany(mappedBy = "edition", fetch = FetchType.LAZY)
            private Collection<Rule> rules;
        */

    public Edition(){}

    public Long getId_edition() {
        return id_edition;
    }

    public void setId_edition(Long id_edition) {
        this.id_edition = id_edition;
    }

    public Long getId_library() {
        return id_library;
    }

    public void setId_library(Long id_library) {
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