package be.PXLResearch.code4belgium.escaperooms.DTO.EscapeRoomDTO;

import be.PXLResearch.code4belgium.general.DTO.BranchDTO.BranchRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EscapeRoomRequest extends BranchRequest {
    public EscapeRoomRequest() {}
}
