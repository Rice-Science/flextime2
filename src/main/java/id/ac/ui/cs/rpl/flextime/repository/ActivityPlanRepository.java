package id.ac.ui.cs.rpl.flextime.repository;

import id.ac.ui.cs.rpl.flextime.model.ActivityPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ActivityPlanRepository extends JpaRepository<ActivityPlan, UUID> {
    ActivityPlan findActivityPlanByUser_Id(UUID user_id);
}
