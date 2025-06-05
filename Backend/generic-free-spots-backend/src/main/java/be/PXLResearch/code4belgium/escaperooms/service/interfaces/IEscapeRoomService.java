package be.PXLResearch.code4belgium.escaperooms.service.interfaces;


import be.PXLResearch.code4belgium.escaperooms.DTO.EscapeRoomDTO.EscapeRoomRequest;
import be.PXLResearch.code4belgium.escaperooms.DTO.EscapeRoomDTO.EscapeRoomResponse;
import be.PXLResearch.code4belgium.escaperooms.domain.EscapeRoom;

import java.io.IOException;
import java.util.List;

public interface IEscapeRoomService {
    List<EscapeRoomResponse> getAllEscapeRooms();
    EscapeRoomResponse getEscapeRoomById(Long id);
    EscapeRoom createEscapeRoom(EscapeRoomRequest request) throws IOException;
    void deleteEscapeRoom(Long id);
}
