package example.submitred_courses.entities;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@AllArgsConstructor
@Data
public class SubmittedCourseResponseDto {

    private Long id;
    private String courseName;
    private Long courseId;
    private Long studentId;
    private Integer grade;
    private Boolean isAccepted;
    private Instant placedDate;
    private Instant submittedDate;
    private Boolean isChangeable;
}
