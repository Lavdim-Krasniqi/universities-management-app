package example.program.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@AllArgsConstructor
@Data
public class ProgramRequestDto {

    public Long id;
    public String name;
    public String level;
    public Integer numberOfSemester;
    public Long departmentId;
    public Instant createdDate;
    public Instant lastModifiedDate;
}
