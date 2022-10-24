package example.profesor.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.util.Date;

@AllArgsConstructor
@Data
public class ProfessorResponseDto {

    public Long id;
    public String name;
    public String surname;
    public Integer personalNumber;
    public String email;
    public String gender;
    public Date birthday;
    public Instant createdDate;
    public Instant lastModifiedDate;
}
