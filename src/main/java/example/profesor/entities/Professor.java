package example.profesor.entities;

import example.course.entities.Course;
import example.program.entities.Program;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "professor", indexes = {
        @Index(name = "personalNumber", columnList = "personalNumber", unique = true),
        @Index(name = "email", columnList = "email", unique = true)
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private Integer personalNumber;
    private String email;
    private String gender;
    private Date birthday;
    @CreationTimestamp
    private Instant createdDate;
    @UpdateTimestamp
    private Instant lastModifiedDate;

    @ManyToMany(mappedBy = "professors", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    private Set<Program> programs = new HashSet<>();

    @OneToMany(mappedBy = "professor")
    @JsonIgnore
    private Set<Course> courses = new HashSet<>();
}
