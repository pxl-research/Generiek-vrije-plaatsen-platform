package be.PXLResearch.code4belgium.escaperooms.DTO.RoomDto;

import be.PXLResearch.code4belgium.escaperooms.domain.EscapeRoom;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomRequest {
    private String name;
    private int minimumAge;
    private int duration;
    private int minPlayers;
    private int maxPlayers;
    private EscapeRoom escapeRoom;
}
