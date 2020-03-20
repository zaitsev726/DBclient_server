package Entities.Types;

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

    @Override
    public String toString() {
        return "Worker{" +
                "address='" + address + '\'' +
                ", firm='" + firm + '\'' +
                '}';
    }
}
