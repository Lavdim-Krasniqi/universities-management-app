package example.semester.repositories;

import example.semester.entities.Semester;
import example.semester.entities.SemesterResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, Long> {

    @Query("select  new example.semester.entities.SemesterResponseDto(s.id,s.program.id,s.number,s.level,s.description,s.credits,s.passability,s.startDate,s.endDate,s.createdDate,s.lastModifiedDate)" +
            " from Semester s where s.program.id = ?1")
    Page<SemesterResponseDto> getSemester(Long programId, Pageable pageable);

    @Modifying
    @Transactional
    @Query("delete from Semester s where s.program.id = ?1")
    void deleteByProgramId(Long programId);

    @Modifying
    @Transactional
    @Query("delete from Semester s where s.program.id in ?1")
    void deleteByProgramsIds(List<Long> programsIds);

    @Query("select case when count(s)>0 then true else false end from Semester s where s.id = ?1 and s.program.id = ?2")
    boolean checkIfGivenProgramIdMatchesInGivenSemester(Long semesterId, Long programId);
}
