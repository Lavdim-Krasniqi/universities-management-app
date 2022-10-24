package example.semester.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@AllArgsConstructor
@Data
public class SemesterResponseDto {

    public Long id;
    public Long program_id;
    public Integer number;
    public String level;
    public String description;
    public Integer credits;
    public Integer passability;
    public Instant startDate;
    public Instant endDate;
    public Instant createdDate;
    public Instant lastModifiedDate;

}
