package helmes.test.sectors.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "users")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @NotBlank(message = "Name must not be empty!")
    @Size(min = 2, max = 26)
    private String name;

    @OneToMany(targetEntity = UserSector.class, fetch = FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @NotEmpty(message = "You need to choose atleast one sector!")
    private List<UserSector> sectors = new ArrayList<>();

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

    public List<UserSector> getSectors() {
        return sectors;
    }

    public void setSectors(List<UserSector> sectors) {
        if(sectors == null) {
            this.sectors = sectors;
        } else {
            this.sectors.clear();
            this.sectors.addAll(sectors);
        }
    }
}
