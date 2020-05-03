package Entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table( name = "Libraries")
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_library;

    @Column(name = "quantity")
    private Long halls_num;

    @OneToMany(mappedBy = "library", fetch = FetchType.EAGER)
    private Collection<AllReader> readers;

    @OneToMany(mappedBy = "work_library", fetch = FetchType.EAGER)
    private Collection<Librarian> librarians;

    @OneToMany(mappedBy = "book_library", fetch = FetchType.EAGER)
    private Collection<Edition> editions;

    public Library() {
    }

    public Long getId_library() { return id_library; }

    public void setId_library(Long id_library) { this.id_library = id_library; }

    public Long getHalls_num() { return halls_num; }

    public void setHalls_num(Long halls_num) { this.halls_num = halls_num; }

    public Collection<AllReader> getReaders() { return readers; }

    public void setReaders(Collection<AllReader> readers) { this.readers = readers; }

}
