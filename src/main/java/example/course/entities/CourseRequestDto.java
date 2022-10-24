package example.course.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CourseRequestDto {

    public String name;
    public Integer credits;
    public String type;
    public Long programId;
    public Integer semesterNumber;
    public Long professorId;
}
