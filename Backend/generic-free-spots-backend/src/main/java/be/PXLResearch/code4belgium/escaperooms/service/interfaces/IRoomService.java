package be.PXLResearch.code4belgium.escaperooms.service.interfaces;


import be.PXLResearch.code4belgium.escaperooms.DTO.RoomDto.RoomRequest;
import be.PXLResearch.code4belgium.escaperooms.DTO.RoomDto.RoomResponse;
import be.PXLResearch.code4belgium.escaperooms.domain.Room;

import java.util.List;

public interface IRoomService {
    List<RoomResponse> getRooms();
    List<RoomResponse> getRoomsForEscapeRoom(Long escapeRoomId);
    RoomResponse getRoomById(Long roomId);
    Room createRoom(RoomRequest request);
}
