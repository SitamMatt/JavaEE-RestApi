package sitammatt.example_rest.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "TASK")
@NamedQuery(name = "Task.findAll", query = "SELECT t FROM Task t")
public class Task extends BaseEntity implements Serializable {

    private String title;
    private String description;

    public Task() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
