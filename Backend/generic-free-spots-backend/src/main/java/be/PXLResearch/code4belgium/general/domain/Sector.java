package be.PXLResearch.code4belgium.general.domain;

import be.PXLResearch.code4belgium.general.Organization;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sectors", indexes = {
        @Index(name = "idx_sector_name", columnList = "name"),
        @Index(name = "idx_sector_image", columnList = "image")
})
public class Sector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image;

    @OneToMany
    private List<Organization<?>> organizations;

    @ManyToMany
    @JoinTable(
            name = "sector_filters",
            joinColumns = @JoinColumn(name = "sector_id"),
            inverseJoinColumns = @JoinColumn(name = "filter_id")
    )
    private List<Filter> filters;

}
