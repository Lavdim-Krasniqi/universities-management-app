package example.faculty.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class FacultyDto {
    private Long Id;
    private String name;
    private String location;
    private Long universityId;
    private Instant createdDate;
    private Instant lastModifiedDate;
}
