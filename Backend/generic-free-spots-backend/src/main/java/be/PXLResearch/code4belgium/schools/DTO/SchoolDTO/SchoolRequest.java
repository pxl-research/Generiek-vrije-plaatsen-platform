package be.PXLResearch.code4belgium.schools.DTO.SchoolDTO;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchoolRequest {
    private String name;
    private String description;
    private Long organizationId;
    private String address;
    private int postalCode;
    private String city;
    private String email;
    private String phoneNumber;
    private String website;
    private int maxCapacity;
    private JsonNode filterableProperties;
}
