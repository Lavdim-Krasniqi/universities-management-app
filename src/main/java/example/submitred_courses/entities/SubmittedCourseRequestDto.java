package example.submitred_courses.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@AllArgsConstructor
@Data
public class SubmittedCourseRequestDto {

    private Long deadlineId;
    private Long studentId;
    private Long courseId;
}
