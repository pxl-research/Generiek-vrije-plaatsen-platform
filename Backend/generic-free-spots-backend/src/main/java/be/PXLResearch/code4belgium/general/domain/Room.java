package be.PXLResearch.code4belgium.general.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class Room<T> {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private float price;
    private int minimumAge;
    private int duration;
    private int currentCapacity;
    private int maxCapacity;
}
