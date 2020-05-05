package Appli.Entities;

import javax.persistence.*;
import java.util.Collection;

//@Entity
//@Table(name = "Librarians")
public class Librarian {
  /*  @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_librarian;

    @Column
    private Long id_library;

    @Column
    private Long hall_num;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_library", referencedColumnName = "id_library", updatable = false, insertable = false)
    private Library work_library;

    @OneToMany(mappedBy = "librarian", fetch = FetchType.LAZY)
    private Collection<IssuedBook> issuedBooks;

    public Librarian() { }

    public Long getId_librarian() { return id_librarian; }

    public void setId_librarian(Long id_librarian) { this.id_librarian = id_librarian; }

    public Long getId_library() { return id_library; }

    public void setId_library(Long id_library) { this.id_library = id_library; }

    public Long getHall_num() { return hall_num; }

    public void setHall_num(Long hall_num) { this.hall_num = hall_num; }

    public Library getWork_library() { return work_library; }

    public void setWork_library(Library work_library) { this.work_library = work_library; }*/
}
