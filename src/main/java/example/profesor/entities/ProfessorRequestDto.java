package example.profesor.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class ProfessorRequestDto {

    public String name;
    public String surname;
    public Integer personalNumber;
    public String email;
    public String gender;
    public Date birthday;
}
