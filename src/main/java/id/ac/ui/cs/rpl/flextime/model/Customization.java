package id.ac.ui.cs.rpl.flextime.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.util.UUID;

@Entity
@Table(name = "customization")
@Getter @Setter
public class Customization {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "customer_training_id", nullable = false)
    private CustomerTraining training;

    @ManyToOne
    @JoinColumn(name = "session_plan_id", nullable = false)
    private SessionPlan sessionPlan;

    private int setCount;
    private int repCount;
    private Duration timeCount;
}
