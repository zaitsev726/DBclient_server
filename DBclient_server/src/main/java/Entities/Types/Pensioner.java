package Entities.Types;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pensioners")
public class Pensioner extends AbstractReader {
    @Column
    private int id_pensioners;                     //номер пенсионного удостоверения

    public Pensioner(){super("pensioner");}
    public Pensioner(int id_pensioners){this.id_pensioners = id_pensioners;}

    public int getId_pensioners() { return id_pensioners; }

    public void setId_pensioners(int id_pensioners) { this.id_pensioners = id_pensioners; }

    @Override
    public String toString() {
        return "Pensioner{" +
                "id_pensioners=" + id_pensioners +
                '}';
    }
}
