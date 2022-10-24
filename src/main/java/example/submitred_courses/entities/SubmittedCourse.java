package example.submitred_courses.entities;

import example.course.entities.Course;
import example.deadline.entities.Deadline;
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

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "submitted_course")
@Getter
@Setter
public class SubmittedCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer grade;
    private Boolean isAccepted;
    private Instant placedDate;
    private Instant submittedDate;
    @CreationTimestamp
    private Instant createdDate;
    @UpdateTimestamp
    private Instant lastModifiedDate;

    @ManyToOne
    @JoinColumn(name = "deadline_id")
    @JsonIgnore
    private Deadline deadline;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    Course course;
}
