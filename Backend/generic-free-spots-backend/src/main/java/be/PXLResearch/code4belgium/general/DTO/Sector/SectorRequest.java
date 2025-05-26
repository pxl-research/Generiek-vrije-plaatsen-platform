package be.PXLResearch.code4belgium.general.DTO.Sector;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectorRequest {
    private String name;
    private String image;
}
