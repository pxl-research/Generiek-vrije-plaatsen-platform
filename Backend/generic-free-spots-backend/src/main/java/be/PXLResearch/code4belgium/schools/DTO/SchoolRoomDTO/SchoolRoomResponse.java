package be.PXLResearch.code4belgium.schools.DTO.SchoolRoomDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchoolRoomResponse {
    private Long id;
    private String name;
    private int minimumAge;
    private int duration;
    private int minStudents;
    private int maxStudents;
}
