package be.PXLResearch.code4belgium.escaperooms.domain;

import be.PXLResearch.code4belgium.enums.City;
import be.PXLResearch.code4belgium.general.Organization;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "escape_rooms", indexes = {
        @Index(name = "idx_escaperoom_name", columnList = "name"),
        @Index(name = "idx_escaperoom_createdat", columnList = "createdAt"),
        @Index(name = "idx_escaperoom_city_postalcode", columnList = "city, postalCode"),
        @Index(name = "idx_escaperoom_maxcapacity", columnList = "maxCapacity")
})
public class EscapeRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

//    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
//    private LocalDateTime createdAt;
//
//    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
//    private LocalDateTime updatedAt;

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
    private EscapeRoomOrganization organization;

    @OneToMany(mappedBy = "escapeRoom")
    private List<Room> rooms;

    @Type(JsonBinaryType.class)
    @Column(columnDefinition = "jsonb")
    private JsonNode filterableProperties;
}
