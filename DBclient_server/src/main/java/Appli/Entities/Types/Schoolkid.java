package Appli.Entities.Types;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "schoolkids")
public class Schoolkid extends AbstractReader {
    @Column
    private Long id_school;                      //номер школы
    @Column
    private Long grade;                          //номер класса

    public Schoolkid(){super("schoolkid");}

    public Long getId_school() { return id_school; }

    public void setId_school(Long id_school) { this.id_school = id_school; }

    public Long getGrade() { return grade; }

    public void setGrade(Long grade) { this.grade = grade; }

    @Override
    public String toString() {
        return  super.toString() +
                "Schoolkid{" +
                "id_school=" + id_school +
                ", grade=" + grade +
                '}';
    }
}
