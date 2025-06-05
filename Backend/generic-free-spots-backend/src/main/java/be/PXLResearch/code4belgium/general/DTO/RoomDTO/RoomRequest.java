package be.PXLResearch.code4belgium.general.DTO.RoomDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class RoomRequest {
    private String name;
    private int minimumAge;
    private int duration;
    private Long branchId;
    private int currentCapacity;
    private int maxCapacity;
}
