package id.ac.ui.cs.rpl.flextime.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
import java.util.List;

@Entity
@Table(name = "training_list")
@Getter @Setter
public class TrainingList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToMany
    @JoinColumn(name = "training_list_id", nullable = false)
    private List<Training> trainings;
}
