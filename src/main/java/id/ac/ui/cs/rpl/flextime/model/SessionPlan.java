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

    @ManyToMany
    @JoinTable(
            name = "session_plan_trainings",
            joinColumns = @JoinColumn(name = "session_plan_id"),
            inverseJoinColumns = @JoinColumn(name = "training_id")
    )
    private List<Training> trainings = new ArrayList<>();

    public Training addTraining(Training training) {
        this.trainings.add(training);
        return training;
    }
}
