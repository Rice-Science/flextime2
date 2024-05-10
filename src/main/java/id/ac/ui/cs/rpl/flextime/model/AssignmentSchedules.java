package id.ac.ui.cs.rpl.flextime.model;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter @Setter
@Table(name = "tbl_assignment")
public class AssignmentSchedules {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID AssignmentSchedulesId;

    @Column(name = "assignment_name")
    private String AssignmentSchedulesTitle;

    @Column(name = "assignment_deadline")
    private Date AssignmentSchedulesDeadline; // datatype prop should be changed to something IDK

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;
}