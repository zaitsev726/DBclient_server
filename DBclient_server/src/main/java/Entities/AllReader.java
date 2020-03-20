package Entities;

import Entities.Types.AbstractReader;

import javax.persistence.*;

@Entity
@Table(name = "allreaders")
public class AllReader{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id_reader;                      //Id читателя
    @Column
    private String type;                        //Тип читателя
    @Column
    private String surname;                     //Фамилия
    @Column
    private String name;                        //Имя
    @Column
    private String patronymic;                  //Отчество
    @Column
    private int id_library;                     //Id библиотеки в которой зарегистрирован

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_reader")
    private AbstractReader readerType;

    public AllReader() { }

    public AllReader(int id_reader, String type,String surname, String name, String patronymic, int id_library) {
        this.id_reader = id_reader;
        this.type = type;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.id_library = id_library;
        this.type = type;
    }

    public String getSurname() { return surname; }

    public void setSurname(String surname) { this.surname = surname; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getPatronymic() { return patronymic; }

    public void setPatronymic(String patronymic) { this.patronymic = patronymic; }

    public int getId_library() { return id_library; }

    public void setId_library(int id_library) { this.id_library = id_library; }



}
