package Application.Entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "characteristic")
public class Characteristic {
    @Id
    @GeneratedValue(generator = "characteristic_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "characteristic_generator", sequenceName = "Characteristics_generator", allocationSize = 1)
    private Long id_edition;                    //ID издания

    @Column
    private String type_edition;                //Тип издания (книга, журнал и т.д.)

    @Column
    private String author;                      //Автор издания

    @Column
    private String title;                       //Название издания


    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_edition", nullable = true)
    private Edition edition;


    public Edition getEdition() {
        return edition;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "edition")
    private Collection<Information> information;

    public Collection<Information> getInformation() { return information; }

    public void setInformation(Collection<Information> information) { this.information = information; }

    public Long getId_edition() {
        return id_edition;
    }

    public void setId_edition(Long id_edition) {
        this.id_edition = id_edition;
    }

    public String getType_edition() {
        return type_edition;
    }

    public void setType_edition(String type_edition) {
        this.type_edition = type_edition;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public String toString() {
        return "Characteristic{" +
                "id_edition=" + id_edition +
                ", type_edition='" + type_edition + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
