package example.deadline.entities;

import example.program.entities.Program;
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
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "deadline", indexes = {
        @Index(name = "deadline_index", columnList = "program_id, startDate, endDate")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Deadline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Instant startDate;
    private Instant endDate;
    private String level;
    private String type;
    @CreationTimestamp
    private Instant createdDate;
    @UpdateTimestamp
    private Instant lastModifiedDate;
    @ManyToOne
    @JoinColumn(name = "program_id")
    @JsonIgnore
    Program program;

    @OneToMany(mappedBy = "deadline")
    @JsonIgnore
    Set<SubmittedCourse> submittedCourses = new HashSet<>();
}
