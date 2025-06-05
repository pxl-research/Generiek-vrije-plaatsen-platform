package be.PXLResearch.code4belgium.nurseries.DTO.NurseryOrganizationDTO;

import be.PXLResearch.code4belgium.general.DTO.OrganizationDTO.OrganizationRequest;
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
public class NurseryOrganizationRequest extends OrganizationRequest<NurseryOrganization, Nursery> {
    public NurseryOrganizationRequest() {}
}
