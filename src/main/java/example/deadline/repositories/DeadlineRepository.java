package example.deadline.repositories;

import example.deadline.entities.Deadline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public interface DeadlineRepository extends JpaRepository<Deadline, Long> {

    @Query("select case when count(d)>0 then true else false end from Deadline d where d.program.id = ?1 and d.endDate > ?2")
    boolean checkIfDeadlineIsOpen(Long programId, Instant time);

    @Query(value = "select d.* from deadline d where d.program_id = ?1 and d.end_Date > ?2 order by d.id DESC LIMIT 1 ", nativeQuery = true)
    Deadline getOpenDeadline(Long programId, Instant time);

    @Query("select case when count(d)>0 then true else false end from Deadline d where d.id = ?1 and d.endDate < ?2")
    boolean checkIfDeadlineIsExpired(Long deadlineId, Instant time);

}
