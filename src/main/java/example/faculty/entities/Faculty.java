package example.faculty.entities;

import example.department.entities.Department;
import example.university.entities.University;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "faculty", indexes = {
        @Index(name = "faculty_index", columnList = "name, location, university_id", unique = true)
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "faculty_id")
    private Long id;
    private String name;
    private String location;
    @CreationTimestamp
    private Instant createdDate;
    @UpdateTimestamp
    private Instant lastModifiedDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id")
    @JsonIgnore
    private University university;

    @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Department> departments = new HashSet<>();
}
