package be.PXLResearch.code4belgium.escaperooms.DTO.EscapeRoomDto;

import be.PXLResearch.code4belgium.escaperooms.domain.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EscapeRoomResponse {
    private Long id;
    private String name;
    private String address;
    private int postalCode;
    private String city;
    private String email;
    private String phoneNumber;
    private String website;
    private List<Room> rooms;
}
