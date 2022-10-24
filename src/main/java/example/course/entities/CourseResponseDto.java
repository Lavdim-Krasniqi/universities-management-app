package example.course.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CourseResponseDto {

    public Long id;
    public String name;
    public Integer credits;
    public String type;
    public Long programId;
    public Long professorId;
    public Integer semesterNumber;
}
