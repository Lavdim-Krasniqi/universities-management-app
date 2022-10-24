package example.department.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@AllArgsConstructor
@Data
public class DepartmentRequestDto {

    public Long id;
    public String name;
    public Long facultyId;
    public Instant createdDate;
    public Instant lastModifiedDate;

}
