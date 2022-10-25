package by.roman.company.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "technology")
@Entity
public class Technology implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "technology_name")
    private String name;

    @Override
    public String toString() {
        return "Technology " +
                "id = " + id +
                " name = " + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Technology technology = (Technology) o;
        return id.equals(technology.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
