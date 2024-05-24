package id.ac.ui.cs.rpl.flextime.repository;
import id.ac.ui.cs.rpl.flextime.model.AssignmentSchedules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface AssignmentSchedulesRepository extends JpaRepository<AssignmentSchedules, UUID> {
    List<AssignmentSchedules> findAssignmentByCustomer_Id(UUID customer_id);
    List<AssignmentSchedules> findAllByAssignmentSchedulesDeadline(LocalDateTime deadline);
}