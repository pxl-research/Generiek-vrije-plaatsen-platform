package be.PXLResearch.code4belgium.escaperooms.DTO.EscapeRoomOrganizationDTO;

import be.PXLResearch.code4belgium.enums.City;
import be.PXLResearch.code4belgium.escaperooms.domain.EscapeRoom;
import be.PXLResearch.code4belgium.escaperooms.domain.EscapeRoomOrganization;
import be.PXLResearch.code4belgium.general.DTO.OrganizationDTO.OrganizationResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EscapeRoomOrganizationResponse extends OrganizationResponse<EscapeRoomOrganization, EscapeRoom> {
    public EscapeRoomOrganizationResponse() {}
}
