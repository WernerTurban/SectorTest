package helmes.test.sectors.model;

import java.util.List;

public class SectorData {
    private Integer id;

    private String name;
    private Integer value;
    private String parentSectorName;
    private List<SectorData> children;

    public SectorData(Integer id, String name, Integer value, String parentSectorName, List<SectorData> children) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.parentSectorName = parentSectorName;
        this.children = children;
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

    public List<SectorData> getChildren() {
        return children;
    }

    public void setChildren(List<SectorData> children) {
        this.children = children;
    }
}
