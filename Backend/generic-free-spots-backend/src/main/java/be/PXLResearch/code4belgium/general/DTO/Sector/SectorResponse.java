package be.PXLResearch.code4belgium.general.DTO.Sector;

import be.PXLResearch.code4belgium.general.domain.Filter;
import be.PXLResearch.code4belgium.general.Organization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectorResponse {
    private Long id;
    private String name;
    private String image;
    private List<Organization<?>> organizations;
    private List<Filter> filters;
}
