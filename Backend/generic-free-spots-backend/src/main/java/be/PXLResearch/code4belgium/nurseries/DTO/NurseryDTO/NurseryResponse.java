package be.PXLResearch.code4belgium.nurseries.DTO.NurseryDTO;

import be.PXLResearch.code4belgium.enums.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NurseryResponse {
    private Long id;
    private String name;
    private String address;
    private int postalCode;
    private City city;
    private String email;
    private String phoneNumber;
    private String website;
    private int currentCapacity;
    private int maxCapacity;
}
