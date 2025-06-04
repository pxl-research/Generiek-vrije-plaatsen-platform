package be.PXLResearch.code4belgium.nurseries.DTO.NurseryOrganizationDTO;

import be.PXLResearch.code4belgium.nurseries.domain.Nursery;
import be.PXLResearch.code4belgium.nurseries.domain.NurseryOrganization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NurseryOrganizationRequest {
    private Long parentOrganizationId;
    private List<NurseryOrganization> childrenOrganizations;
    private List<Nursery> schools;
    private Long sectorId;
    private String name;
    private String type;
    private String address;
    private String city;
    private int postalCode;

}
