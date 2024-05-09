package id.ac.ui.cs.rpl.flextime.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "training")
@Getter @Setter
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToMany (cascade = CascadeType.ALL)
    @JoinColumn(
            name = "customization_id",
            referencedColumnName = "id",
            nullable = true
    )
    private List<Customization> customization;

    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String trainingType;

    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private User admin;
}
