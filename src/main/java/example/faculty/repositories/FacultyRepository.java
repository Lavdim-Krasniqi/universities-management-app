package example.faculty.repositories;

import example.faculty.entities.Faculty;
import example.faculty.entities.FacultyDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    @Query("select f.id from Faculty f where f.university.id = ?1")
    List<Long> getFacultiesOfGivenUniversity(Long universityId);

    @Modifying
    @Transactional
    @Query("delete from Faculty u where u.university.id = ?1")
    void deleteFacultyByUniversityId(Long universityId);

    @Query("select new example.faculty.entities.FacultyDto(f.id,f.name,f.location,f.university.id,f.createdDate,f.lastModifiedDate)" +
            " from Faculty  f where f.university.id = ?1")
    Page<FacultyDto> getAllFaculties(Long universityId, Pageable pageable);
}
