package Application.Entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "information")
public class Information implements Serializable {
    @Id
    @GeneratedValue(generator = "information_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "information_generator", sequenceName = "Information_generator", allocationSize = 1)
    private  Long id_record;                //ID записи информации

    @Id
    private Long id_edition;                //ID издания, к которому относится запись

    @Column
    private String author;                  //Автор произведения

    @Column
    private String composition;             //Название произведения

    @Column
    private int popularity;                 //Популярность

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_edition", referencedColumnName = "id_edition", updatable = false, insertable = false)
    private Characteristic edition;

    public Characteristic getEdition() { return edition; }

    public void setEdition(Characteristic edition) { this.edition = edition; }

    public Long getId_record() { return id_record; }

    public void setId_record(Long id_record) { this.id_record = id_record; }

    public String getAuthor() { return author; }

    public Long getId_edition() { return id_edition; }

    public void setId_edition(Long id_edition) { this.id_edition = id_edition; }


    public void setAuthor(String author) { this.author = author; }

    public String getComposition() { return composition; }

    public void setComposition(String composition) { this.composition = composition; }

    public int getPopularity() { return popularity; }

    public void setPopularity(int popularity) { this.popularity = popularity; }

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
