package be.PXLResearch.code4belgium.general.DTO.Filter;

import be.PXLResearch.code4belgium.enums.DataType;
import be.PXLResearch.code4belgium.general.domain.Sector;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterResponse {
    private Long id;
    private String name;
    private DataType datatype;
    private String value;
    private List<Sector> sectors;
    private boolean active;
    private String inputType;
}
