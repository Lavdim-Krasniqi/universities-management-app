package example.semester.entities;

import example.program.entities.Program;
import example.student.entities.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "semester", indexes = {
        @Index(name = "semester_index", columnList = "program_id, number, startDate, endDate", unique = true)
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Semester {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer number;
    private String level;
    private String description;
    private Integer credits;
    private Integer passability;
    private Instant startDate;
    private Instant endDate;
    @CreationTimestamp
    private Instant createdDate;
    @UpdateTimestamp
    private Instant lastModifiedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id")
    @JsonIgnore
    private Program program;
    
    @ManyToMany(mappedBy = "semesters")
    private Set<Student> students = new HashSet<>();
}
