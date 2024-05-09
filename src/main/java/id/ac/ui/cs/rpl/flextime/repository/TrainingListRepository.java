package id.ac.ui.cs.rpl.flextime.repository;

import id.ac.ui.cs.rpl.flextime.model.TrainingList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TrainingListRepository extends JpaRepository<TrainingList, UUID> {
}
