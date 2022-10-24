package example.profesor.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.util.Date;

@AllArgsConstructor
@Data
public class ProfessorProgramSemesterDto {

    public Long professorId;
    public String professorName;
    public String surname;
    public String email;
    public String gender;
    public Date birthday;
    public Long programId;
    public String programName;
    public Long semesterId;
}
