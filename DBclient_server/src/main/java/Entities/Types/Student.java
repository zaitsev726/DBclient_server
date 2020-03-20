package Entities.Types;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Student extends AbstractReader {
    @Column
    private int id_university;                      //id университета
    @Column
    private String faculty;                         //название факультета
    @Column
    private int id_group;                           //номер группы

    public Student(){super("student");}
    public Student(int id_university, String faculty, int id_group){
        this.id_university = id_university;
        this.faculty = faculty;
        this.id_group = id_group;
    }

    public int getId_university() { return id_university; }

    public void setId_university(int id_university) { this.id_university = id_university; }

    public String getFaculty() { return faculty; }

    public void setFaculty(String faculty) { this.faculty = faculty; }

    public int getId_group() { return id_group; }

    public void setId_group(int id_group) { this.id_group = id_group; }

    @Override
    public String toString() {
        return "Student{" +
                "id_university=" + id_university +
                ", faculty='" + faculty + '\'' +
                ", id_group=" + id_group +
                '}';
    }
}
