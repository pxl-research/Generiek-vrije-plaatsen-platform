package be.PXLResearch.code4belgium.schools.DTO.SchoolDTO;

import be.PXLResearch.code4belgium.enums.City;
import be.PXLResearch.code4belgium.general.DTO.BranchDTO.BranchResponse;
import be.PXLResearch.code4belgium.schools.domain.SchoolRoom;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SchoolResponse extends BranchResponse<SchoolRoom> {
    public SchoolResponse() {}
}
