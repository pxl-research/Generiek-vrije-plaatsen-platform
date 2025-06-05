package be.PXLResearch.code4belgium.general.DTO.OrganizationDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class OrganizationRequest<T, U> {
    private Long parentOrganizationId;
    private List<T> childrenOrganizations;
    private List<U> branches;
    private Long sectorId;
    private String name;
    private String type;
    private String address;
    private String city;
    private int postalCode;
}
