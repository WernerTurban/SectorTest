package helmes.test.sectors.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "sectors")
public class Sector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Integer value;
    private String parentSectorName;

    public Sector(Integer id, String name, Integer value, String parentSectorName
    ) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.parentSectorName = parentSectorName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getParentSectorName() {
        return parentSectorName;
    }

    public void setParentSectorName(String parentSectorName) {
        this.parentSectorName = parentSectorName;
    }

    public Sector() {

    }
}
