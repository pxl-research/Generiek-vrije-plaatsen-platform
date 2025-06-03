package be.PXLResearch.code4belgium.schools.domain;

import be.PXLResearch.code4belgium.enums.City;
import be.PXLResearch.code4belgium.escaperooms.domain.Room;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.List;

@Entity
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "schools", indexes = {
        @Index(name = "idx_school_name", columnList = "name"),
        @Index(name = "idx_school_city_postalcode", columnList = "city, postalCode"),
        @Index(name = "idx_school_maxcapacity", columnList = "maxCapacity")
})
public class School {
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
    private SchoolOrganization organization;

    @OneToMany(mappedBy = "school")
    private List<SchoolRoom> schoolRooms;

    @Type(JsonBinaryType.class)
    @Column(columnDefinition = "jsonb")
    private JsonNode filterableProperties;
}
