package Appli.Entities;

import javax.persistence.*;
import java.util.Date;

//@Entity
//@Table (name = "IssuedBooks")
public class IssuedBook {
   /* @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_record;

    @Column
    private Long id_reader;

    @Column
    private Long id_edition;

    @Column
    private Date date_extradition;

    @Column
    private Date date_return;

    @Column
    private boolean is_returned;

    @Column
    private Long id_librarian;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_librarian", referencedColumnName = "id_librarian", updatable = false, insertable = false)
    private Librarian librarian;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_reader", referencedColumnName = "id_reader", updatable = false, insertable = false)
    private AllReader reader;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_edition", referencedColumnName = "id_edition", updatable = false, insertable = false)
    private Edition edition;

    public Long getId_record() {
        return id_record;
    }

    public void setId_record(Long id_record) {
        this.id_record = id_record;
    }

    public Long getId_reader() {
        return id_reader;
    }

    public void setId_reader(Long id_reader) {
        this.id_reader = id_reader;
    }

    public Long getId_edition() {
        return id_edition;
    }

    public void setId_edition(Long id_edition) {
        this.id_edition = id_edition;
    }

    public Date getDate_extradition() {
        return date_extradition;
    }

    public void setDate_extradition(Date date_extradition) {
        this.date_extradition = date_extradition;
    }

    public Date getDate_return() {
        return date_return;
    }

    public void setDate_return(Date date_return) {
        this.date_return = date_return;
    }

    public boolean isIs_returned() {
        return is_returned;
    }

    public void setIs_returned(boolean is_returned) {
        this.is_returned = is_returned;
    }

    public Long getId_librarian() {
        return id_librarian;
    }

    public void setId_librarian(Long id_librarian) {
        this.id_librarian = id_librarian;
    }

    public Librarian getLibrarian() {
        return librarian;
    }

    public void setLibrarian(Librarian librarian) {
        this.librarian = librarian;
    }

    public AllReader getReader() {
        return reader;
    }

    public void setReader(AllReader reader) {
        this.reader = reader;
    }

    public Edition getEdition() {
        return edition;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
    }*/
}
