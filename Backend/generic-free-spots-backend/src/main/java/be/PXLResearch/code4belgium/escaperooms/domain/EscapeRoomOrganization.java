package be.PXLResearch.code4belgium.escaperooms.domain;

import be.PXLResearch.code4belgium.general.Organization;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "escape_room_organizations")
public class EscapeRoomOrganization extends Organization<EscapeRoom> {
    // Empty Constructor needed for proper compilation
    public EscapeRoomOrganization() {}
}
