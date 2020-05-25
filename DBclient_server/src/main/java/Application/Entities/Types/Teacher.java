package Application.Entities.Types;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "teachers")
public class Teacher extends AbstractReader {
    @Column
    private Long id_university;                  //id университета/школы
    @Column
    private String faculty;                     //название факультета

    public Teacher(){super("teacher");}

    public Long getId_university() { return id_university; }

    public void setId_university(Long id_university) { this.id_university = id_university; }

    public String getFaculty() { return faculty; }

    public void setFaculty(String faculty) { this.faculty = faculty; }

    @Override
    public String toString() {
        return  super.toString() +
                "Подробные сведения о профессии учитель \n" +
                "ID университета: " + id_university + '\n' +
                "Название факлуьтета: " + faculty;
    }
}
