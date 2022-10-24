package example.department.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class DepartmentResponseDto {

    public Long id;
    public String name;
    public Long facultyId;
    public Instant createdDate;
    public Instant lastModifiedDate;

}
