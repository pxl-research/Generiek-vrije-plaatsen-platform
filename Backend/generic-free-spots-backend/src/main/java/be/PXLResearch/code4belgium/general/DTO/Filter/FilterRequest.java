package be.PXLResearch.code4belgium.general.DTO.Filter;

import be.PXLResearch.code4belgium.enums.DataType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterRequest {
    private String name;
    private String datatype;
    private String value;
    private boolean active;
    private String inputType;
}
