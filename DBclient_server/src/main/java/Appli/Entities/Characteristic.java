package Appli.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Entity
//@Table(name = "characteristic")
public class Characteristic {
   /* @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_edition;

    @Column
    private String type_edition;

    @Column
    private String author;

    @Column
    private String title;

    @OneToOne(optional = false, mappedBy = "characteristic")
    private Edition edition;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    private List<Information> information = new ArrayList<>();


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

    public Edition getEdition() {
        return edition;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
    }

    public List<Information> getInformation() {
        return information;
    }

    public void setInformation(List<Information> information) {
        this.information = information;
    }*/
}
