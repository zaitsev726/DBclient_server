package Application.Entities.Types;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "scientists")
public class Scientist extends AbstractReader {
    @Column
    private String address;                         //адрес места работы
    @Column
    private Long id_university;                      //id университета

    public Scientist(){super("scientist");}

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public Long getId_university() { return id_university; }

    public void setId_university(Long id_university) { this.id_university = id_university; }

    @Override
    public String toString() {
        return  super.toString() +
                "Подробные сведения о профессии ученый \n" +
                "Адрес работы: '" + address + '\n' +
                "ID университета: " + id_university;
    }
}
