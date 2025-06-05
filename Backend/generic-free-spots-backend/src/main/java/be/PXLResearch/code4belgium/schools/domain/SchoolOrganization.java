package be.PXLResearch.code4belgium.schools.domain;

import be.PXLResearch.code4belgium.general.domain.Organization;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "school_organizations")
public class SchoolOrganization extends Organization<School> {
    // Empty Constructor needed for proper compilation
    public SchoolOrganization() {}
}
