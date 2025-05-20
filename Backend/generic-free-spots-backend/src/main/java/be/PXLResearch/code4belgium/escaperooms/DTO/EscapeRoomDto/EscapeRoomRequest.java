package be.PXLResearch.code4belgium.escaperooms.DTO.EscapeRoomDto;

import be.PXLResearch.code4belgium.enums.City;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EscapeRoomRequest {
    private String name;
    private String address;
    private int postalCode;
    private String city;
    private String email;
    private String phoneNumber;
    private String website;
    private JsonNode filterableProperties;
}
