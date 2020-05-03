package Entities;

import Entities.Types.AbstractReader;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "allreaders")
public class AllReader{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id_reader;                      //Id читателя

    @Column
    private String type;                        //Тип читателя

    @Column
    private String surname;                     //Фамилия

    @Column
    private String name;                        //Имя

    @Column
    private String patronymic;                  //Отчество

    @Column
    private Long id_library;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_library", referencedColumnName = "id_library")
    private Library library;                     //Id библиотеки в которой зарегистрирован

    @OneToOne(optional = false, cascade = CascadeType.ALL)      //??????
    @JoinColumn(name = "id_reader")
    private AbstractReader readerType;

    @OneToMany(mappedBy = "reader")
    private Collection<IssuedBook> ourBooks;

    public AllReader() { }

    public String getSurname() { return surname; }

    public void setSurname(String surname) { this.surname = surname; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getPatronymic() { return patronymic; }

    public void setPatronymic(String patronymic) { this.patronymic = patronymic; }

    public Long getId_reader() { return id_reader; }

    public void setId_reader(Long id_reader) { this.id_reader = id_reader; }

    public Library getLibrary() { return library; }

    public void setLibrary(Library library) { this.library = library; }

    public Long getId_library() { return id_library; }

    public void setId_library(Long id_library) { this.id_library = id_library; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public AbstractReader getReaderType() { return readerType; }

    public void setReaderType(AbstractReader readerType) { this.readerType = readerType; }

    public Collection<IssuedBook> getOurBooks() { return ourBooks; }

    public void setOurBooks(Collection<IssuedBook> ourBooks) { this.ourBooks = ourBooks; }

    @Override
    public String toString() {
        return "AllReader{" +
                "id_reader=" + id_reader +
                ", type='" + type + '\'' +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", id_library=" + id_library +
                ", library=" + library +
                ", readerType=" + readerType +
                '}';
    }
}
