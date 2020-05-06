package Appli.Entities;

import Appli.Entities.Types.AbstractReader;

import javax.persistence.*;

@Entity
@Table(name = "allreaders")
public class AllReader {
    @Id
    @GeneratedValue(generator = "allreader_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "allreader_generator", sequenceName = "AllReaders_generator", allocationSize = 1)
    @Column(name = "id_reader")
    private Long id_reader;                      //Id читателя

    @Column(name = "type", nullable = true)
    private String type;                        //Тип читателя

    @Column(name = "surname")
    private String surname;                     //Фамилия

    @Column(name = "name")
    private String name;                        //Имя

    @Column(name = "patronymic")
    private String patronymic;                  //Отчество

    @Column(name = "id_library")
    private Long id_library;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_library", referencedColumnName = "id_library", updatable = false, insertable = false)
    private Library library;                     //Id библиотеки в которой зарегистрирован

    @OneToOne(optional = false, cascade = CascadeType.ALL)      //??????
    @JoinColumn(name = "id_reader", nullable = true)
    private AbstractReader readerType;

    /*  @OneToMany(mappedBy = "reader")
      private Collection<IssuedBook> ourBooks;
  */
    public AllReader() {
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Long getId_reader() {
        return id_reader;
    }

    public void setId_reader(Long id_reader) {
        this.id_reader = id_reader;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
   }

    public Long getId_library() {
        return id_library;
    }

    public void setId_library(Long id_library) {
        this.id_library = id_library;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public AbstractReader getReaderType() {
        return readerType;
    }

    public void setReaderType(AbstractReader readerType) {
        this.readerType = readerType;
    }

    // public Collection<IssuedBook> getOurBooks() { return ourBooks; }
//
    // public void setOurBooks(Collection<IssuedBook> ourBooks) { this.ourBooks = ourBooks; }

    @Override
    public String toString() {
        return "AllReader{" +
                "id_reader=" + id_reader +
                ", type='" + type + '\'' +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", id_library=" + id_library +
                //              ", library=" + library +
                //              ", readerType=" + readerType +
                '}';
        // return "@asda"
    }


}
