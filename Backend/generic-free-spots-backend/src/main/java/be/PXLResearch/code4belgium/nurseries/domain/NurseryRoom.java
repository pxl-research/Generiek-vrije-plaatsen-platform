package be.PXLResearch.code4belgium.nurseries.domain;

import be.PXLResearch.code4belgium.schools.domain.School;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "nursery_rooms")
public class NurseryRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private float price;
    private int minimumAge;
    private int duration;
    private int minKids;
    private int maxKids;
    @ManyToOne
    @JoinColumn(name = "nursery_id", nullable = false)
    private Nursery nursery;
}
