package id.ac.ui.cs.rpl.flextime.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "session_schedule")
@Getter @Setter
public class SessionSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne
    private SessionPlan sessionPlan;

    @Column
    private LocalDateTime start;

    @Column
    private LocalDateTime end;
}
