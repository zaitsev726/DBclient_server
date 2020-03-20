package Entities.Types;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "scientists")
public class Scientist extends AbstractReader {
    @Column
    private String address;                         //адрес места работы
    @Column
    private int id_university;                      //id университета

    public Scientist(){super("scientist");}
    public Scientist(String address, int id_university){
        this.address = address;
        this.id_university = id_university;
    }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public int getId_university() { return id_university; }

    public void setId_university(int id_university) { this.id_university = id_university; }

    @Override
    public String toString() {
        return "Scientist{" +
                "address='" + address + '\'' +
                ", id_university=" + id_university +
                '}';
    }
}
