package by.roman.company.entity;


import by.roman.company.enumerate.LocationEnum;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "course")
@Entity
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "course_name")
    private String name;

    @Column(name = "start_date")
    private LocalDate dateStart;

    @Column(name = "finish_date")
    private LocalDate dateFinish;


    @Column(name = "course_location")
    @Enumerated(value = EnumType.STRING)
    private LocationEnum location;

    @Column(name = "course_description")
    private String description;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_company", updatable = false)
    private Company company;

    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Set<User> users = new HashSet<>();

    @Override
    public String toString() {
        return "Course " +
                "id = " + id +
                ", dateStart = " + dateStart +
                ", dateFinish = " + dateFinish +
                ", description = " + description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id.equals(course.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
