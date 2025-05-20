package be.PXLResearch.code4belgium.general;

import be.PXLResearch.code4belgium.enums.DataType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "filters", indexes = {
        @Index(name = "idx_filter_name", columnList = "name"),
        @Index(name = "idx_filter_datatype", columnList = "datatype"),
        @Index(name = "idx_filter_value", columnList = "value"),
        @Index(name = "idx_filter_active", columnList = "active")
})
public class Filter {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private DataType datatype;
    private String value;
    @ManyToMany(mappedBy = "filters")
    private List<Sector> sectors;
    private boolean active;
}
