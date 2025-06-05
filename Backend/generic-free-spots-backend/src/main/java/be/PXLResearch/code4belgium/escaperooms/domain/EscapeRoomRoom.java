package be.PXLResearch.code4belgium.escaperooms.domain;

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
@Table(name = "escaperoom_rooms")
public class EscapeRoomRoom extends Room<EscapeRoom> {
    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false)
    private EscapeRoom branch;

    public EscapeRoomRoom() {}
}
