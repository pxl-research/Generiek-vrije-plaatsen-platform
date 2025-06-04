package be.PXLResearch.code4belgium.nurseries.DTO.NurseryOrganizationDTO;

import be.PXLResearch.code4belgium.enums.City;
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
public class NurseryOrganizationResponse {
    private Long id;
    private NurseryOrganization parentOrganization;
    private List<NurseryOrganization> childrenOrganizations;
    private List<Nursery> nurseries;
    private String name;
    private String type;
    private String address;
    private City city;
    private int postalCode;
}
