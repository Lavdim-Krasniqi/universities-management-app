package example.student.entities;

import java.time.Instant;

public interface StudentCourses {

    Long getId();

    String getName();

    Integer getCredits();

    String getType();

    Instant getCreated_Date();

    Instant getLast_Modified_Date();

    Long getProfessor_id();

    Long getProgram_id();

    Long getSemester_id();

}
