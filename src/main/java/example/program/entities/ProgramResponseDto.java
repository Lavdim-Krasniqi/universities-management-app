package example.program.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class ProgramResponseDto {

    public Long id;
    public String name;
    public String level;
    public Integer numberOfSemester;
    public Instant createdDate;
    public Instant lastModifiedDate;
    public Long department_id;
}
