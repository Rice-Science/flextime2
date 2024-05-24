package id.ac.ui.cs.rpl.flextime.repository;

import id.ac.ui.cs.rpl.flextime.model.SessionSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SessionScheduleRepository extends JpaRepository<SessionSchedule, UUID>{
    SessionSchedule findSessionScheduleBySessionPlan_Id(UUID id);
}
