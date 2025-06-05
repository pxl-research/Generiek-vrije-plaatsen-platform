package be.PXLResearch.code4belgium.nurseries.DTO.NurseryOrganizationDTO;

import be.PXLResearch.code4belgium.enums.City;
import be.PXLResearch.code4belgium.general.DTO.OrganizationDTO.OrganizationResponse;
import be.PXLResearch.code4belgium.nurseries.domain.Nursery;
import be.PXLResearch.code4belgium.nurseries.domain.NurseryOrganization;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NurseryOrganizationResponse extends OrganizationResponse<NurseryOrganization, Nursery> {
    public NurseryOrganizationResponse() {}
}
