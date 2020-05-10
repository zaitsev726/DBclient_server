package Appli.Entities;

import Appli.Entities.Types.Pensioner;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Collection;

@Entity
@Table( name = "libraries")
public class Library {
    @Id
  //  @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_library;

    @Column(name = "quantity")
    private Long halls_num;


    @OneToMany(mappedBy = "library", fetch = FetchType.EAGER)
    private Collection<AllReader> readers;
/*
    @OneToMany(mappedBy = "work_library", fetch = FetchType.LAZY)
    private Collection<Librarian> librarians;

    @OneToMany(mappedBy = "book_library", fetch = FetchType.LAZY)
    private Collection<Edition> editions;
*/
    public Library() {
    }

    public Long getId_library() { return id_library; }

    public void setId_library(Long id_library) { this.id_library = id_library; }

    public Long getHalls_num() { return halls_num; }

    public void setHalls_num(Long halls_num) { this.halls_num = halls_num; }


    public Collection<AllReader> getReaders() {
        return readers; }

   public void setReaders(Collection<AllReader> readers) { this.readers = readers; }

    @Override
    public String toString() {
        return "Library{" +
                "id_library=" + id_library +
                ", halls_num=" + halls_num +
                '}';
    }
}
