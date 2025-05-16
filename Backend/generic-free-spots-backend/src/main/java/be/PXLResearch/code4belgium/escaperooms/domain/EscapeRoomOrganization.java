package be.PXLResearch.code4belgium.escaperooms.domain;

import be.PXLResearch.code4belgium.general.Organization;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "escape_room_organizations")
public class EscapeRoomOrganization extends Organization<EscapeRoom> {

}
