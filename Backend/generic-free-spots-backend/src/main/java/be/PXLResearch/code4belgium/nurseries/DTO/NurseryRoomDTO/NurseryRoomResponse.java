package be.PXLResearch.code4belgium.nurseries.DTO.NurseryRoomDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NurseryRoomResponse {
    private Long id;
    private String name;
    private int minimumAge;
    private int duration;
    private int minKids;
    private int maxKids;
}
