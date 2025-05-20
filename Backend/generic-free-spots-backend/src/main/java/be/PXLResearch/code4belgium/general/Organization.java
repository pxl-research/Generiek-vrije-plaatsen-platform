package be.PXLResearch.code4belgium.general;

import be.PXLResearch.code4belgium.enums.City;
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
    private Organization<T> parentOrganization;

    @OneToMany(mappedBy = "parentOrganization")
    private List<Organization<T>> childOrganizations;

    @OneToMany(mappedBy = "organization")
    private List<T> freeSpots;

    @ManyToOne
    @JoinColumn(name = "sector_id")
    private Sector sector;
    private String name;
    private String type; //VZW, NV, BVBA, ...
    private String address;

    @Enumerated(EnumType.STRING)
    private City city;
    private int postalCode;
}
