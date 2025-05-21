package be.PXLResearch.code4belgium.escaperooms.DTO.EscapeRoomOrganizationDTO;

import be.PXLResearch.code4belgium.enums.City;
import be.PXLResearch.code4belgium.escaperooms.domain.EscapeRoom;
import be.PXLResearch.code4belgium.escaperooms.domain.EscapeRoomOrganization;
import be.PXLResearch.code4belgium.general.Sector;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EscapeRoomOrganizationRequest {
    private Long parentOrganizationId;
    private List<EscapeRoomOrganization> childrenOrganizations;
    private List<EscapeRoom> escapeRooms;
    private Long sectorId;
    private String name;
    private String type;
    private String address;
    private String city;
    private int postalCode;

}
