package id.ac.ui.cs.rpl.flextime.model;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.util.Date;

@Getter @Setter
@Table(name = "tbl_assignment")
@Entity
public class AssignmentSchedules {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String AssignmentSchedulesId;

    @Column(name = "assignment_name")
    private String AssignmentSchedulesTitle;

    @Column(name = "assignment_deadline")
    private String AssignmentSchedulesDeadline;
}