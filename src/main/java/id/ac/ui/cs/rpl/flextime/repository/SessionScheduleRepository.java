package id.ac.ui.cs.rpl.flextime.repository;

import id.ac.ui.cs.rpl.flextime.model.SessionPlan;
import id.ac.ui.cs.rpl.flextime.model.SessionSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SessionScheduleRepository extends JpaRepository<SessionSchedule, UUID> {
    SessionSchedule findSessionScheduleBySessionPlan_Id(UUID id);
    List<SessionSchedule> findSessionSchedulesByDayAndSessionPlanIn(String day, List<SessionPlan> sessionPlans);
    List<SessionSchedule> findSessionSchedulesBySessionPlan_FitnessPlan_Customer_Id(UUID customerId);
    void deleteBySessionPlan_Id(UUID id);

}
