package Application.Entities.Types;

import javax.persistence.*;

@Entity
@Table(name = "pensioners")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "insertPensionersWithRandomId",
                procedureName = "insert_pensioner",
                parameters = {
                        @StoredProcedureParameter(
                                name = "id",
                                type = Integer.class
                        ),
                        @StoredProcedureParameter(
                                name = "type",
                                type = String.class
                        )
                }
        )
})
public class Pensioner extends AbstractReader {
    @Column
    private Long id_pensioners;                     //номер пенсионного удостоверения

    public Pensioner(){super("pensioner");}

    public Long getId_pensioners() { return id_pensioners; }

    public void setId_pensioners(Long id_pensioners) { this.id_pensioners = id_pensioners; }

    @Override
    public String toString() {
        return  super.toString() +
                "Подробные сведения о профессии пенсионер \n" +
                "Номер пенсионного удостоверения: " + id_pensioners;
    }
}
