package Appli.Entities.Types;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pensioners")
public class Pensioner extends AbstractReader {
    @Column
    private Long id_pensioners;                     //номер пенсионного удостоверения

    public Pensioner(){super("pensioner");}
    public Long getId_pensioners() { return id_pensioners; }

    public void setId_pensioners(Long id_pensioners) { this.id_pensioners = id_pensioners; }

    @Override
    public String toString() {
        return  super.toString() +
                "Pensioner{" +
                "id_pensioners=" + id_pensioners +
                '}';
    }
}
