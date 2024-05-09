package id.ac.ui.cs.rpl.flextime.repository;

import id.ac.ui.cs.rpl.flextime.model.Customization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomizationRepository extends JpaRepository<Customization, UUID> {
}
