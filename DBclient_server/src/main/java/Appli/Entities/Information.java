package Appli.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "information")
public class Information implements Serializable {
    @Id
    @GeneratedValue(generator = "information_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "information_generator", sequenceName = "Information_generator", allocationSize = 1)
    private  Long id_record;

    @Id
    private Long id_edition;

    @Column
    private String author;

    @Column
    private String composition;

    @Column
    private Long popularity;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_edition", referencedColumnName = "id_edition", updatable = false, insertable = false)
    private Characteristic edition;

    public Long getId_record() { return id_record; }

    public void setId_record(Long id_record) { this.id_record = id_record; }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { this.author = author; }

    public String getComposition() { return composition; }

    public void setComposition(String composition) { this.composition = composition; }

    public Long getPopularity() { return popularity; }

    public void setPopularity(Long popularity) { this.popularity = popularity; }

    @Override
    public String toString() {
        return "Information{" +
                "id_record=" + id_record +
                ", id_edition=" + id_edition +
                ", author='" + author + '\'' +
                ", composition='" + composition + '\'' +
                ", popularity=" + popularity +
                '}';
    }
}
