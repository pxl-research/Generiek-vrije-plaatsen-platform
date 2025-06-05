package be.PXLResearch.code4belgium.nurseries.DTO.NurseryRoomDTO;

import be.PXLResearch.code4belgium.general.DTO.RoomDTO.RoomResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NurseryRoomResponse extends RoomResponse {
    public NurseryRoomResponse() {}
}
