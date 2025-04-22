package be.PXLResearch.code4belgium.escaperooms.service.interfaces;


import be.PXLResearch.code4belgium.escaperooms.DTO.EscapeRoomDto.EscapeRoomRequest;
import be.PXLResearch.code4belgium.escaperooms.DTO.EscapeRoomDto.EscapeRoomResponse;

import java.util.List;

public interface IEscapeRoomService {
    List<EscapeRoomResponse> getAllEscapeRooms();
    EscapeRoomResponse getEscapeRoomById(Long id);
    EscapeRoomResponse addEscapeRoom(EscapeRoomRequest request);
}
