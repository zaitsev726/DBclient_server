package Entities.Types;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "schoolkids")
public class Schoolkid extends AbstractReader {
    @Column
    private int id_school;                      //номер школы
    @Column
    private int grade;                          //номер класса

    public Schoolkid(){super("schoolkid");}
    public Schoolkid(int id_school, int grade){
        this.id_school = id_school;
        this.grade = grade;
    }

    public int getId_school() { return id_school; }

    public void setId_school(int id_school) { this.id_school = id_school; }

    public int getGrade() { return grade; }

    public void setGrade(int grade) { this.grade = grade; }

    @Override
    public String toString() {
        return "Schoolkid{" +
                "id_school=" + id_school +
                ", grade=" + grade +
                '}';
    }
}
