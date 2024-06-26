package id.ac.ui.cs.rpl.flextime.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "session_plan")
@Getter @Setter
public class SessionPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String trainingType;

    @ManyToOne
    @JoinColumn(name = "fitness_plan_id", nullable = false)
    private FitnessPlan fitnessPlan;
}
