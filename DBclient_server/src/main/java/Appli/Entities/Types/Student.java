package Appli.Entities.Types;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Student extends AbstractReader {
    @Column
    private Long id_university;                      //id университета
    @Column
    private String faculty;                         //название факультета
    @Column
    private Long id_group;                           //номер группы

    public Student(){super("student");}

    public Long getId_university() { return id_university; }

    public void setId_university(Long id_university) { this.id_university = id_university; }

    public String getFaculty() { return faculty; }

    public void setFaculty(String faculty) { this.faculty = faculty; }

    public Long getId_group() { return id_group; }

    public void setId_group(Long id_group) { this.id_group = id_group; }

    @Override
    public String toString() {
        return  super.toString() +
                "Student{" +
                "id_university=" + id_university +
                ", faculty='" + faculty + '\'' +
                ", id_group=" + id_group +
                '}';
    }
}
