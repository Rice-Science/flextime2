package id.ac.ui.cs.rpl.flextime.repository;
import id.ac.ui.cs.rpl.flextime.model.ClassSchedules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClassSchedulesRepository extends JpaRepository<ClassSchedules, UUID> {
}