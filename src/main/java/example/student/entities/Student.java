package example.student.entities;

import example.course.entities.Course;
import example.program.entities.Program;
import example.semester.entities.Semester;
import example.submitred_courses.entities.SubmittedCourse;
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
@Table(name = "student", indexes = {
        @Index(name = "personalNumber_index", columnList = "personalNumber", unique = true),
        @Index(name = "email_index", columnList = "email", unique = true)
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private Integer personalNumber;
    private Date birthday;
    private String gender;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private Boolean isGraduated;
    private Integer credits;
    private String level;
    @CreationTimestamp
    private Instant createdDate;
    @UpdateTimestamp
    private Instant lastModifiedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id")
    @JsonIgnore
    private Program program;

    @ManyToMany
    @JoinTable(name = "student_courses",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    @JsonIgnore
    private Set<Course> courses = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "student_semesters",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "semester_id"))
    @JsonIgnore
    private Set<Semester> semesters = new HashSet<>();

    @OneToMany(mappedBy = "student")
    @JsonIgnore
    private Set<SubmittedCourse> submittedCourses = new HashSet<>();
}
