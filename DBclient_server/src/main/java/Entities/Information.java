package Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Information")
public class Information {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id_record;

    @Column
    private String author;

    @Column
    private String title;

    @Column
    private String composition;

    @Column
    private Long popularity;

    @ManyToMany(mappedBy = "information")
    private List<Characteristic> Char = new ArrayList<>();

    public Long getId_record() { return id_record; }

    public void setId_record(Long id_record) { this.id_record = id_record; }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { this.author = author; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getComposition() { return composition; }

    public void setComposition(String composition) { this.composition = composition; }

    public Long getPopularity() { return popularity; }

    public void setPopularity(Long popularity) { this.popularity = popularity; }

    public List<Characteristic> getChar() { return Char; }

    public void setChar(List<Characteristic> aChar) { Char = aChar; }
}
