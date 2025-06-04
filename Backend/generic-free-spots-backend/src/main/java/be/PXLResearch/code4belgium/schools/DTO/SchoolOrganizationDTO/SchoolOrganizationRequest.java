package be.PXLResearch.code4belgium.schools.DTO.SchoolOrganizationDTO;

import be.PXLResearch.code4belgium.escaperooms.domain.EscapeRoom;
import be.PXLResearch.code4belgium.escaperooms.domain.EscapeRoomOrganization;
import be.PXLResearch.code4belgium.schools.domain.School;
import be.PXLResearch.code4belgium.schools.domain.SchoolOrganization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolOrganizationRequest {
    private Long parentOrganizationId;
    private List<SchoolOrganization> childrenOrganizations;
    private List<School> schools;
    private Long sectorId;
    private String name;
    private String type;
    private String address;
    private String city;
    private int postalCode;

}
