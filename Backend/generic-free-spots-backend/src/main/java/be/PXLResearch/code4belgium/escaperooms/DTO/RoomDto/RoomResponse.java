package be.PXLResearch.code4belgium.escaperooms.DTO.RoomDto;

import be.PXLResearch.code4belgium.escaperooms.domain.EscapeRoom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomResponse {
    private Long id;
    private String name;
    private int minimumAge;
    private int duration;
    private int minPlayers;
    private int maxPlayers;
    
}
