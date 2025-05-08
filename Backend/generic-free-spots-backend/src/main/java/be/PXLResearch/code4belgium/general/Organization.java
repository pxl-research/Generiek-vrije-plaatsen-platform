package be.PXLResearch.code4belgium.general;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public abstract class Organization<T> {
    private Long id;
    private List<T> freeSpots;
    private String name;
}
