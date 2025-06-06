package be.PXLResearch.code4belgium.schools.domain;

import be.PXLResearch.code4belgium.general.domain.Room;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "school_rooms")
public class SchoolRoom extends Room<School> {
    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false)
    @JsonBackReference
    private School branch;

    public SchoolRoom() {}
}
