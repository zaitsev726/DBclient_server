package Entities.Types;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pensioners")
public class Teacher extends AbstractReader {
    @Column
    private int id_university;                  //id университета/школы
    @Column
    private String faculty;                     //название факультета

    public Teacher(){super("teacher");}
    public Teacher(int id_university, String faculty){
        this.id_university = id_university;
        this.faculty = faculty;
    }

    public int getId_university() { return id_university; }

    public void setId_university(int id_university) { this.id_university = id_university; }

    public String getFaculty() { return faculty; }

    public void setFaculty(String faculty) { this.faculty = faculty; }

    @Override
    public String toString() {
        return "Teacher{" +
                "id_university=" + id_university +
                ", faculty='" + faculty + '\'' +
                '}';
    }
}
