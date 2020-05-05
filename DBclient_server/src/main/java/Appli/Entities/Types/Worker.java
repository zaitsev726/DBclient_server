package Appli.Entities.Types;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "workers")
public class Worker extends AbstractReader {
    @Column
    private String address;                     //адрес фирмы
    @Column
    private String firm;                        //фирма

    public Worker() { super("worker"); }
    public Worker(String address, String firm){
        this.address = address;
        this.firm = firm;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    @Override
    public String toString() {
        return  super.toString() +
                "Worker{" +
                "address='" + address + '\'' +
                ", firm='" + firm + '\'' +
                '}';
    }
}
