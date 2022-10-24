package example.student.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Date;

@AllArgsConstructor
@Data
public class StudentResponseDto {

    public Long id;
    public String name;
    public String surname;
    public Integer personalNumber;
    public Date birthday;
    public String gender;
    public String email;
    public String password;
    public String phoneNumber;
    public String address;
    public Boolean isGraduated;
    public Instant createdDate;
    public Instant lastModifiedDate;
    public Long programId;
}
