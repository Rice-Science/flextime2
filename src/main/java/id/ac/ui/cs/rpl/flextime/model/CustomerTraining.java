package id.ac.ui.cs.rpl.flextime.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "customer_training")
@Getter @Setter
public class CustomerTraining {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "training_id")
    private Training training;

    @ManyToOne
    @JoinColumn(name = "session_plan_id")
    private SessionPlan sessionPlan;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "customization_id")
    private Customization customization;

    public int getDurationInSeconds() {
        int sets = this.customization.getSetCount();
        int reps = this.customization.getRepCount();
        int duration = this.customization.getDurationInSeconds();
        return sets * reps * duration;
    }
}
