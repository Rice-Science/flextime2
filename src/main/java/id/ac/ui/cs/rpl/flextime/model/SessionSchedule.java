package id.ac.ui.cs.rpl.flextime.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
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
    private String day;

    @Column
    private LocalTime startTime;

    @Column
    private LocalTime endTime;

    public String getStartTimeString() {
        return startTime.toString();
    }

    public String getEndTimeString() {
        return endTime.toString();
    }
}
