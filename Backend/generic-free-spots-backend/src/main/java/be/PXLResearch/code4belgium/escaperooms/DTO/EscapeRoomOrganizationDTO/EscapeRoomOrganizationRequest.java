package be.PXLResearch.code4belgium.escaperooms.DTO.EscapeRoomOrganizationDTO;

import be.PXLResearch.code4belgium.escaperooms.domain.EscapeRoom;
import be.PXLResearch.code4belgium.escaperooms.domain.EscapeRoomOrganization;
import be.PXLResearch.code4belgium.general.DTO.OrganizationDTO.OrganizationRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EscapeRoomOrganizationRequest extends OrganizationRequest<EscapeRoomOrganization, EscapeRoom> {
    public EscapeRoomOrganizationRequest() {}
}
