package be.PXLResearch.code4belgium.schools.DTO.SchoolOrganizationDTO;

import be.PXLResearch.code4belgium.general.DTO.OrganizationDTO.OrganizationResponse;
import be.PXLResearch.code4belgium.schools.domain.School;
import be.PXLResearch.code4belgium.schools.domain.SchoolOrganization;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SchoolOrganizationResponse extends OrganizationResponse<SchoolOrganization, School> {
    public SchoolOrganizationResponse() {}
}
