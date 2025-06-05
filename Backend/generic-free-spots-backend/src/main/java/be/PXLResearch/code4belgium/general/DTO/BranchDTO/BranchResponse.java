package be.PXLResearch.code4belgium.general.DTO.BranchDTO;

import be.PXLResearch.code4belgium.enums.City;
import be.PXLResearch.code4belgium.escaperooms.domain.EscapeRoomRoom;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class BranchResponse<T> {
    private Long id;
    private String name;
    private String address;
    private int postalCode;
    private City city;
    private String email;
    private String phoneNumber;
    private String website;
    private List<T> rooms;
}
