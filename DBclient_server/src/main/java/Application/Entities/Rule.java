package Application.Entities;

import javax.persistence.*;

@Entity
@Table(name = "rules")
public class Rule {

    @Id
    @GeneratedValue(generator = "rules_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "rules_generator", sequenceName = "Rules_generator", allocationSize = 1)
    private Long id_rule;               //ID правила

    @Column
    private Long id_edition;            //ID издания, к которому относится правило

    @Column
    private String rule;                //Само правило

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_edition", referencedColumnName = "id_edition", updatable = false, insertable = false)
    private Edition edition;

    public Long getId_rule() { return id_rule; }

    public void setId_rule(Long id_rule) { this.id_rule = id_rule; }

    public Long getId_edition() { return id_edition; }

    public void setId_edition(Long id_edition) { this.id_edition = id_edition; }

    public String getRule() { return rule; }

    public void setRule(String rule) { this.rule = rule; }

    public Edition getEdition() { return edition; }

    public void setEdition(Edition edition) { this.edition = edition; }

    @Override
    public String toString() {
        return "Rule{" +
                "id_rule=" + id_rule +
                ", id_edition=" + id_edition +
                ", rule='" + rule + '\'' +
                '}';
    }
}
