package be.PXLResearch.code4belgium.escaperooms.service;

import be.PXLResearch.code4belgium.escaperooms.DTO.RoomDto.RoomRequest;
import be.PXLResearch.code4belgium.escaperooms.DTO.RoomDto.RoomResponse;
import be.PXLResearch.code4belgium.escaperooms.domain.EscapeRoom;
import be.PXLResearch.code4belgium.escaperooms.domain.Room;
import be.PXLResearch.code4belgium.escaperooms.repository.EscapeRoomRepository;
import be.PXLResearch.code4belgium.escaperooms.repository.RoomRepository;
import be.PXLResearch.code4belgium.escaperooms.service.interfaces.IRoomService;
import be.PXLResearch.code4belgium.exceptions.ResourceNotFoundException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService implements IRoomService {
    private final RoomRepository roomRepository;
    private final EscapeRoomRepository escapeRoomRepository;

    @Override
    public List<RoomResponse> getRooms() {
        List<Room> rooms = roomRepository.findAll();
        if (rooms.isEmpty()) {
            throw new ResourceNotFoundException("No rooms found");
        }

        return rooms.stream()
                .map(e -> new RoomResponse(
                        e.getId(),
                        e.getName(),
                        e.getMinimumAge(),
                        e.getDuration(),
                        e.getMinPlayers(),
                        e.getMaxPlayers(),
                        e.getEscapeRoom()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomResponse> getRoomsForEscapeRoom(Long escapeRoomId) {
        EscapeRoom escapeRoom = escapeRoomRepository.findById(escapeRoomId).orElseThrow(() -> new ResourceNotFoundException("No escape room found with ID " + escapeRoomId));

        return escapeRoom.getRooms().stream()
                .map(e -> new RoomResponse(
                        e.getId(),
                        e.getName(),
                        e.getMinimumAge(),
                        e.getDuration(),
                        e.getMinPlayers(),
                        e.getMaxPlayers(),
                        e.getEscapeRoom()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public RoomResponse getRoomById(Long roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new ResourceNotFoundException("No room found with ID " + roomId));

        return RoomResponse.builder()
                .id(room.getId())
                .name(room.getName())
                .minimumAge(room.getMinimumAge())
                .duration(room.getDuration())
                .minPlayers(room.getMinPlayers())
                .maxPlayers(room.getMaxPlayers())
                .escapeRoom(room.getEscapeRoom())
                .build();
    }

    @Override
    public Room createRoom(RoomRequest request) {
        Room room = Room.builder()
                .name(request.getName())
                .minimumAge(request.getMinimumAge())
                .duration(request.getDuration())
                .minPlayers(request.getMinPlayers())
                .maxPlayers(request.getMaxPlayers())
                .escapeRoom(request.getEscapeRoom())
                .build();

        return roomRepository.save(room);
    }
}
