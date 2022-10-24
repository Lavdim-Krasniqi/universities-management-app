package example.deadline.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@AllArgsConstructor
@Data
public class DeadlineRequestDto {

    public String name;
    public Instant startDate;
    public Instant endDate;
    public String level;
    public String type;
    public Long programId;
}
