package example.semester.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@AllArgsConstructor
@Data
public class SemesterRequestDto {

    public Long program_id;
    public Integer number;
    public String level;
    public String description;
    public Integer credits;
    public Integer passability;
    public Instant startDate;
    public Instant endDate;

}
