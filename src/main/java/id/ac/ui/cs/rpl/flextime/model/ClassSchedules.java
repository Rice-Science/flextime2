package id.ac.ui.cs.rpl.flextime.model;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.util.UUID;
import java.util.Date;
import java.time.Duration;

@Entity
@Getter @Setter
@Table(name = "tbl_class")
public class ClassSchedules {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID classSchedulesId;

    @Column(name = "class_name")
    private String classSchedulesTitle;

    @Column(name = "class_duration")
    private Duration classSchedulesDuration;

    @Column(name = "class_date")
    private Date classSchedulesDate;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;
}