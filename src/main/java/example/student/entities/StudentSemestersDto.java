package example.student.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class StudentSemestersDto {

    public Long studentId;
    public String name;
    public Long semesterId;
    public Long programId;
    public Integer number;
    public String level;
    public String description;
    public Integer credits;
    public Integer passability;
    public Instant startDate;
    public Instant endDate;
}
