package be.PXLResearch.code4belgium.nurseries.domain;

import be.PXLResearch.code4belgium.general.domain.Room;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "nursery_rooms")
public class NurseryRoom extends Room<Nursery> {
    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false)
    private Nursery branch;

    public NurseryRoom() {}
}
