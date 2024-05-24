package id.ac.ui.cs.rpl.flextime.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name="tbl_activity")
public class ActivityPlan {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID activityId;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="class_schedule_id")
    private ClassSchedules classSchedules;

    @ManyToOne
    @JoinColumn(name="assignment_schedule_id ")
    private AssignmentSchedules assignmentSchedules;

    @ManyToOne
    @JoinColumn(name="test_schedule_id")
    private TestSchedules testSchedules;

    @ElementCollection
    private Map<LocalDateTime, UUID> sessionSchedules;

    public ActivityPlan() {
        this.sessionSchedules = new HashMap<>();
    }
}
