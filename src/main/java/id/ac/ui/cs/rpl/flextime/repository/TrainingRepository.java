package id.ac.ui.cs.rpl.flextime.repository;

import id.ac.ui.cs.rpl.flextime.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TrainingRepository extends JpaRepository<Training, UUID>{
}
