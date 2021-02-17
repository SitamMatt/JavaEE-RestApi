package sitammatt.example_rest.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "TASK")
public class Task extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private String title;
    private String description;

    public Task() {
    }

    public Task(@NotNull UUID guid) {
        super(guid);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
