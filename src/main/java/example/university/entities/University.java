package example.university.entities;

import example.faculty.entities.Faculty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "university", indexes = {
        @Index(name = "university_index", columnList = "name, state, city", unique = true)
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String state;
    private String city;
    @CreationTimestamp
    private Instant createdDate;
    @UpdateTimestamp
    private Instant lastModifiedDate;
    @OneToMany(mappedBy = "university", fetch = FetchType.EAGER)
    private Set<Faculty> faculties = new HashSet<>();
}
