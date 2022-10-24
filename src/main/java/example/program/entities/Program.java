package example.program.entities;

import example.course.entities.Course;
import example.deadline.entities.Deadline;
import example.department.entities.Department;
import example.profesor.entities.Professor;
import example.semester.entities.Semester;
import example.student.entities.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "program", indexes = {
        @Index(name = "program_index", columnList = "name,level,department_id", unique = true)
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "program_id")
    private Long id;
    private String name;
    private String level;
    private Integer numberOfSemester;
    @CreationTimestamp
    private Instant createdDate;
    @UpdateTimestamp
    private Instant lastModifiedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    @JsonIgnore
    private Department department;

    @OneToMany(mappedBy = "program")
    @JsonIgnore
    private Set<Semester> semesters = new HashSet<>();

    @OneToMany(mappedBy = "program")
    @JsonIgnore
    private Set<Student> students = new HashSet<>();

    @OneToMany(mappedBy = "program")
    @JsonIgnore
    private Set<Course> courses = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "program_professors", joinColumns = @JoinColumn(name = "program_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id"))
    @JsonIgnore
    private Set<Professor> professors = new HashSet<>();

    @OneToMany(mappedBy = "program")
    @JsonIgnore
    private Set<Deadline> deadlines = new HashSet<>();

}
