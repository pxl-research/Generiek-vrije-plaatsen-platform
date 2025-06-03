package be.PXLResearch.code4belgium.schools.domain;

import be.PXLResearch.code4belgium.escaperooms.domain.EscapeRoom;
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
@Table(name = "school_rooms")
public class SchoolRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private float price;
    private int minimumAge;
    private int duration;
    private int minStudents;
    private int maxStudents;
    @ManyToOne
    @JoinColumn(name = "school_id", nullable = false)
    private School school;
}
