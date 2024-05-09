package id.ac.ui.cs.rpl.flextime.repository;

import id.ac.ui.cs.rpl.flextime.model.SessionPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SessionPlanRepository extends JpaRepository<SessionPlan, UUID> {
}
