package be.PXLResearch.code4belgium.escaperooms.service.interfaces;


import be.PXLResearch.code4belgium.escaperooms.DTO.EscapeRoomRoomDTO.EscapeRoomRoomRequest;
import be.PXLResearch.code4belgium.escaperooms.DTO.EscapeRoomRoomDTO.EscapeRoomRoomResponse;
import be.PXLResearch.code4belgium.escaperooms.domain.EscapeRoomRoom;

import java.util.List;

public interface IEscapeRoomRoomService {
    List<EscapeRoomRoomResponse> getRooms();
    List<EscapeRoomRoomResponse> getRoomsForEscapeRoom(Long escapeRoomId);
    EscapeRoomRoomResponse getRoomById(Long roomId);
    EscapeRoomRoom createRoom(EscapeRoomRoomRequest request);
    void deleteEscapeRoomRoom(Long id);
    void updateEscapeRoomRoom(Long id, EscapeRoomRoomRequest request);
}
