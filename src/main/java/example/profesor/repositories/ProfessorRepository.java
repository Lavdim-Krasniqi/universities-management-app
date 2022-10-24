package example.profesor.repositories;

import example.profesor.entities.Professor;
import example.profesor.entities.ProfessorProgramResponseDto;
import example.profesor.entities.ProfessorProgramSemesterDto;
import example.profesor.entities.ProfessorResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    @Query("select new example.profesor.entities.ProfessorResponseDto(p.id,p.name,p.surname,p.personalNumber,p.email,p.gender," +
            "p.birthday,p.createdDate,p.lastModifiedDate) from Professor p")
    Page<ProfessorResponseDto> getAllProfessors(Pageable pageable);

    @Query("select new example.profesor.entities.ProfessorResponseDto(p.id,p.name,p.surname,p.personalNumber,p.email,p.gender," +
            "p.birthday,p.createdDate,p.lastModifiedDate) from Professor p join p.programs pr where pr.id = ?1")
    Page<ProfessorResponseDto> getAllProfessorsOfGivenProgram(Long programId, Pageable pageable);

    @Query(value = "select new example.profesor.entities.ProfessorProgramResponseDto(p.id,p.name,p.surname,p.email,p.gender," +
            "p.birthday,p.createdDate,p.lastModifiedDate,pr.id,pr.department.id) " +
            "from Professor p join  p.programs pr  where p.id =?1")
    List<ProfessorProgramResponseDto> getProfessorById(Long professorId);

    @Modifying
    @Transactional
    @Query(value = "insert into program_professors (program_id,professor_id) values (?1,?2)", nativeQuery = true)
    void addProfessorProgramRelation(Long programId, Long professorId);

    //  //when I add a course I have to check if programId of course is same as programId of professor
    @Query("select case when count(p)>0 then true else false end from Professor p join p.programs pr where p.id=?1 and pr.id=?2")
    boolean checkIfProfessorIsPartOfGivenProgram(Long professorId, Long programId);

    @Modifying
    @Transactional
    @Query(value = "delete from program_professors p where p.program_id = ?1", nativeQuery = true)
    void deleteProgramProfessorRelation(Long programId);

    @Modifying
    @Transactional
    @Query(value = "delete from program_professors p where p.program_id in ?1", nativeQuery = true)
    void deleteProgramProfessorRelations(List<Long> programsIds);

    @Modifying
    @Transactional
    @Query(value = "delete from program_professors p where p.professor_id = ?1", nativeQuery = true)
    void deleteProgramProfessorRelationByProfessorId(Long professorId);

    @Modifying
    @Transactional
    @Query(value = "delete from program_professors p where p.professor_id in ?1", nativeQuery = true)
    void deleteProgramProfessorRelationsByProfessorId(List<Long> professorId);

    @Query("select new example.profesor.entities.ProfessorProgramSemesterDto(p.id,p.name,p.surname,p.email,p.gender,p.birthday,pr.id,pr.name,s.id) " +
            "from Professor p  join p.programs pr  join pr.semesters s where p.id = ?1")
    List<ProfessorProgramSemesterDto> getInfoForProfessor(Long professorId);

}
