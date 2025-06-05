package be.PXLResearch.code4belgium.nurseries.domain;

import be.PXLResearch.code4belgium.general.domain.Branch;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "nurseries")
public class Nursery extends Branch<NurseryOrganization, NurseryRoom> {
    public Nursery() {}
}
