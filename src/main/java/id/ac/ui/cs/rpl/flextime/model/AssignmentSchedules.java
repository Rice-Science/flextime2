package id.ac.ui.cs.rpl.flextime.model;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
@Getter @Setter
@Table(name = "tbl_assignment")
public class AssignmentSchedules {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID assignmentSchedulesId;

    @Column(name = "assignment_name")
    private String assignmentSchedulesTitle;

    @Column(name = "assignment_deadline")
    private LocalDateTime assignmentSchedulesDeadline;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    public String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d yyyy HH:mm");
        return assignmentSchedulesDeadline.format(formatter);
    }
}