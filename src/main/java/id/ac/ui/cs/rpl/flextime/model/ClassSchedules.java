package id.ac.ui.cs.rpl.flextime.model;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

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

    @Transient
    private String classSchedulesDateString;

    @Transient
    private String classSchedulesStartString;

    @Transient
    private String classSchedulesEndString;

    @Column(name = "class_date")
    private String classSchedulesDay;

    @Column(name = "class_start")
    private LocalTime classSchedulesStart;

    @Column(name = "class_end")
    private LocalTime classSchedulesEnd;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

//    public String getFormattedDate() {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d yyyy");
//        return classSchedulesDate.format(formatter);
//    }

    public String getFormattedStart() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return classSchedulesStart.format(formatter);
    }

    public String getFormattedEnd() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return classSchedulesEnd.format(formatter);
    }

//    public LocalDateTime getStartDate() {
//        return LocalDateTime.of(this.classSchedulesDate,this.classSchedulesStart);
//    }
//
//    public LocalDateTime getEndDate() {
//        return LocalDateTime.of(this.classSchedulesDate,this.classSchedulesEnd);
//    }
}
