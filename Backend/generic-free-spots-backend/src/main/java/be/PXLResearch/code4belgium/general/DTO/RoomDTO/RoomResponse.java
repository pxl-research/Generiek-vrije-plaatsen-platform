package be.PXLResearch.code4belgium.general.DTO.RoomDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class RoomResponse {
    private Long id;
    private String name;
    private int minimumAge;
    private int duration;
    private int currentCapacity;
    private int maxCapacity;
}
