package example.university.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UniversityResponseDto {

    public Long id;
    public String name;
    public String state;
    public String city;
    public Instant createdDate;
    public Instant lastModifiedDate;
}
