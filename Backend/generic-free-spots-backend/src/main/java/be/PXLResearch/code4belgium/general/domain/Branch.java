package be.PXLResearch.code4belgium.general.domain;

import be.PXLResearch.code4belgium.enums.City;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
public abstract class Branch<T, U> {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;

    private String address;
    private int postalCode;

    @Enumerated(EnumType.STRING)
    private City city;
    private String email;
    private String phoneNumber;
    private String website;
    @ManyToOne
    @JoinColumn(name = "organization_id")
    private T organization;

    @OneToMany(mappedBy = "branch")
    @JsonManagedReference
    private List<U> rooms;

}
