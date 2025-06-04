package be.PXLResearch.code4belgium.nurseries.domain;

import be.PXLResearch.code4belgium.enums.City;
import be.PXLResearch.code4belgium.schools.domain.SchoolOrganization;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Entity
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "nurseries", indexes = {
        @Index(name = "idx_nursery_name", columnList = "name"),
        @Index(name = "idx_nursery_city_postalcode", columnList = "city, postalCode"),
        @Index(name = "idx_nursery_maxcapacity", columnList = "maxCapacity")
})
public class Nursery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    private String address;
    private int postalCode;

    @Enumerated(EnumType.STRING)
    private City city;
    private String email;
    private String phoneNumber;
    private String website;
    private int currentCapacity;
    private int maxCapacity;
    @ManyToOne
    @JoinColumn(name = "organization_id")
    private NurseryOrganization organization;

    @Type(JsonBinaryType.class)
    @Column(columnDefinition = "jsonb")
    private JsonNode filterableProperties;
}
