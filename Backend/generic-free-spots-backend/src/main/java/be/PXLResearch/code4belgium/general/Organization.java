package be.PXLResearch.code4belgium.general;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class Organization<T> {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = true)
    @JoinColumn(name = "parent_id")
    private Organization<?> parentOrganization;

    @OneToMany(mappedBy = "parentOrganization")
    private List<Organization<?>> childOrganizations;

    @OneToMany(mappedBy = "organization")
    private List<T> freeSpots;

    @ManyToOne
    @JoinColumn(name = "sector_id")
    private Sector sector;
    private String name;
    private String type;
}
