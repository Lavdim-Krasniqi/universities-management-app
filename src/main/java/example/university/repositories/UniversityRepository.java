package example.university.repositories;

import example.university.entities.University;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long> {

    @Query("select case when count(u)>0 then true else false END from University u where u.name = ?1 and u.state=?2 and u.city=?3")
    boolean exists(String name, String state, String city);

    @Query(value = "SELECT new example.university.entities.UniversityResponseDto(u.id,u.name,u.state,u.city,u.createdDate,u.lastModifiedDate) " +
            "FROM University u ")
    Page<University> getAllUniversities(Pageable pageable);
}
