package helmes.test.sectors.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

public class UserData {
    private Integer id;

    @NotBlank(message = "Name must not be empty!")
    @Size(min = 2, max = 26)
    private String name;

    private List<Sector> sectors;

    public UserData(Integer id, String name, List<Sector> sectors) {
        this.id = id;
        this.name = name;
        this.sectors = sectors;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Sector> getSectors() {
        return sectors;
    }

    public void setSectors(List<Sector> sectors) {
        this.sectors = sectors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
