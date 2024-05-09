package id.ac.ui.cs.rpl.flextime.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "fitness_plan")
@Getter @Setter
public class FitnessPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    @OneToMany
    @JoinColumn(name = "fitness_plan_id", nullable = false)
    private List<SessionPlan> sessionPlan;
}
