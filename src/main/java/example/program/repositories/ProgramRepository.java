package example.program.repositories;

import example.program.entities.Program;
import example.program.entities.ProgramResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {

    @Query("select new example.program.entities.ProgramResponseDto(p.id,p.name,p.level,p.numberOfSemester,p.createdDate,p.lastModifiedDate,p.department.id)" +
            " from Program  p where p.department.id = ?1")
    Page<ProgramResponseDto> getAllPrograms(Long departmentId, Pageable pageable);

    @Query("select p.id from Program p where p.department.id in ?1")
    List<Long> getProgramsIdsByListOfDepartmentsIds(List<Long> departmentsIds);

    @Query("select p.id from Program p where p.department.id = ?1")
    List<Long> getProgramsIdsByDepartmentId(Long departmentId);

    @Modifying
    @Transactional
    @Query("delete from Program p where p.department.id in ?1")
    void deleteProgramsByGivenDepartmentsIds(List<Long> departmentsIds);

    @Modifying
    @Transactional
    @Query("delete from Program p where p.department.id = ?1")
    void deleteByDepartmentId(Long departmentId);
}