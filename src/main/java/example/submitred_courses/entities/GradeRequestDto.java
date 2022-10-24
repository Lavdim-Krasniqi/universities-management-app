package example.submitred_courses.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@AllArgsConstructor
@Data
public class GradeRequestDto {

    public Long studentId;
    public Long professorId;
    public Long deadlineId;
    public Long courseId;
    public Integer grade;

}
