package example.submitred_courses.entities;

import example.course.entities.Course;
import example.deadline.entities.Deadline;
import example.student.entities.Student;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@AllArgsConstructor
@Data
public class SubmittedCourseSaveDto {


    public Instant submittedDate;
    public Deadline deadline;
    public Student student;
    public Course course;
}
