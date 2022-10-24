package example.student.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class StudentCoursesDto {

    public Long studentId;
    public String studentName;
    public Long courseId;
    public String courseName;
    public Integer courseCredits;
    public String courseType;
    public Long courseProgramId;
    public Long courseProfessorId;
    public Integer courseSemesterNumber;

}
