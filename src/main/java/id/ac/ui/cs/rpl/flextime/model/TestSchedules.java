package id.ac.ui.cs.rpl.flextime.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.persistence.*;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.time.Duration;

@Entity
@Getter @Setter
@Table(name = "tbl_test")
public class TestSchedules {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID testSchedulesId;

    @Column(name = "test_name")
    private String testSchedulesTitle;

    @Transient
    private String testSchedulesDateString;

    @Transient
    private String testSchedulesStartString;

    @Transient
    private String testSchedulesEndString;

    @Column(name = "test_date")
    private LocalDate testSchedulesDate;

    @Column(name = "test_start")
    private LocalTime testSchedulesStart;

    @Column(name = "test_end")
    private LocalTime testSchedulesEnd;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    public String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d yyyy");
        return testSchedulesDate.format(formatter);
    }

    public String getFormattedStart() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return testSchedulesStart.format(formatter);
    }

    public String getFormattedEnd() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return testSchedulesEnd.format(formatter);
    }

    public LocalDateTime getStartDate() {
        return LocalDateTime.of(this.testSchedulesDate,this.testSchedulesStart);
    }

    public LocalDateTime getEndDate() {
        return LocalDateTime.of(this.testSchedulesDate,this.testSchedulesEnd);
    }
}