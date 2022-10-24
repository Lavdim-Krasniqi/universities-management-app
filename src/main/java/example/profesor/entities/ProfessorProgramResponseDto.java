package example.profesor.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.Instant;
import java.util.Date;

@AllArgsConstructor
@Data
public class ProfessorProgramResponseDto {

    public Long id;
    public String name;
    public String surname;
    private String email;
    private String gender;
    private Date birthday;
    private Instant createdDate;
    private Instant lastModifiedDate;
    public Long programId;
    public Long departmentId;


}
