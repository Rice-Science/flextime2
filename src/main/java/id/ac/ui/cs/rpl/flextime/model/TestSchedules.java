package id.ac.ui.cs.rpl.flextime.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import jakarta.persistence.*;
import java.util.UUID;
import java.time.Duration;

@Entity
@Getter @Setter
@Table(name = "tbl_test")
public class TestSchedules {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID TestSchedulesId;

    @Column(name = "test_name")
    private String TestSchedulesTitle;

    @Column(name = "class_duration")
    private Duration TestSchedulesDuration;

    @Column(name = "class_date")
    private Date TestSchedulesDate;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;
}