package be.PXLResearch.code4belgium.escaperooms.DTO.EscapeRoomDTO;

import be.PXLResearch.code4belgium.enums.City;
import be.PXLResearch.code4belgium.escaperooms.domain.EscapeRoomRoom;
import be.PXLResearch.code4belgium.general.DTO.BranchDTO.BranchResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EscapeRoomResponse extends BranchResponse<EscapeRoomRoom> {
    public EscapeRoomResponse() {}
}
