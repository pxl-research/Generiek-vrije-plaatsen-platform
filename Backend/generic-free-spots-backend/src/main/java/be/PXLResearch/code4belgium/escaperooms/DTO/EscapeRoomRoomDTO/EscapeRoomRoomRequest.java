package be.PXLResearch.code4belgium.escaperooms.DTO.EscapeRoomRoomDTO;

import be.PXLResearch.code4belgium.general.DTO.RoomDTO.RoomRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EscapeRoomRoomRequest extends RoomRequest {
    public EscapeRoomRoomRequest() {}
}
