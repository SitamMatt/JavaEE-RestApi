package sitammatt.example_rest.model;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "Person.findOne", query = "select p from Person p where p.id = :id"),
        @NamedQuery(name = "Person.getAll", query = "select p from Person p")
}
)
public class Person {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String lastName;

    public Person() {
    }

    public Person(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    // getter methods
}
