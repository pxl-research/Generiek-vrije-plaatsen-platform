package be.PXLResearch.code4belgium.nurseries.domain;

import be.PXLResearch.code4belgium.general.Organization;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "nursery_organizations")
public class NurseryOrganization extends Organization<Nursery> {
    // Empty Constructor needed for proper compilation
    public NurseryOrganization() {}
}
