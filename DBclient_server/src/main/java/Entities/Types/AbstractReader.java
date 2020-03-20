package Entities.Types;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractReader{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id_reader;                  //id читателя
    @Column
    private String type;                    //тип читателя

    public AbstractReader(){}
    public AbstractReader(String type){this.type = type;}

    public int getId_reader() { return id_reader; }

    public void setId_reader(int id_reader) { this.id_reader = id_reader; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    @Override
    public String toString() {
        return "AbstractReader{" +
                "id_reader=" + id_reader +
                ", type='" + type + '\'' +
                '}';
    }
}
