package id.ac.ui.cs.rpl.flextime.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.util.UUID;

@Entity
@Table(name = "customization")
@Getter @Setter
public class Customization {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private int setCount;
    private int repCount;
    private int durationInSeconds;
}
