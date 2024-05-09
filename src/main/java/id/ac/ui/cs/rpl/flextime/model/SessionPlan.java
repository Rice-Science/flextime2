package id.ac.ui.cs.rpl.flextime.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "session_plan")
@Getter @Setter
public class SessionPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String trainingType;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(
            name = "training_list_id",
            referencedColumnName = "id"
    )
    private TrainingList trainingList;
}
