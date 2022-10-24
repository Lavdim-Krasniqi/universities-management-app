package example.student.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class StudentRequestDto {

    public String name;
    public String surname;
    public Integer personalNumber;
    public Date birthday;
    public String gender;
    public String email;
    public String password;
    public String phoneNumber;
    public String address;
    public Long programId;
    public String level;
}
