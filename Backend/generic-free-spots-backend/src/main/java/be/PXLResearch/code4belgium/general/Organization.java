package be.PXLResearch.code4belgium.general;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public abstract class Organization<T> {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "organization")
    private List<T> freeSpots;

    @ManyToOne
    @JoinColumn(table = "sector_id")
    private Sector sector;
    private String name;
    private String type;
}
