package be.PXLResearch.code4belgium.general;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public abstract class Filter<T> {
    private String name;
    private String datatype;
    private T value;
    private boolean filterable;
}
