package be.PXLResearch.code4belgium.general;

import be.PXLResearch.code4belgium.general.interfaces.IOrganization;
import be.PXLResearch.code4belgium.general.interfaces.ISector;
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
@Table(name = "sectors")
public class Sector<T extends IOrganization, U> implements ISector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    
    @OneToMany
    private List<T> organizations;

    @OneToMany
    private List<U> filters;

}
