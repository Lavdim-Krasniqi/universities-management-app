package example.department.repositories;

import example.department.entities.Department;
import example.department.entities.DepartmentResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Modifying
    @Transactional
    @Query(value = "insert into department (name,faculty_id) values (?1,?2)", nativeQuery = true)
    void addDepartment(String name, Long faculty_id);

    @Query("select new example.department.entities.DepartmentResponseDto(d.id,d.name,d.faculty.id,d.createdDate,d.lastModifiedDate)" +
            " from Department d where d.faculty.id = ?1")
    Page<DepartmentResponseDto> getAllDepartments(Long facultyId, Pageable pageable);

    @Query("select d.id from Department d where d.faculty.id = ?1")
    List<Long> getDepartmentsOfGivenFaculty(Long facultyId);

    @Query("select d.id from Department d where d.faculty.id in ?1")
    List<Long> getDepartmentsIdsOfEachFaculty(List<Long> facultiesIds);

    @Modifying
    @Transactional
    @Query("delete from Department d where d.faculty.id = ?1")
    void deleteDepartmentByFacultyId(Long facultyId);

    @Modifying
    @Transactional
    @Query("delete from Department d where d.faculty.id in ?1")
    void deleteDepartmentsByFacultiesIds(List<Long> facultiesIds);

}
