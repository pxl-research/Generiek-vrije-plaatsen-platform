package be.PXLResearch.code4belgium.escaperooms.service;

import be.PXLResearch.code4belgium.escaperooms.DTO.EscapeRoomRoomDTO.EscapeRoomRoomRequest;
import be.PXLResearch.code4belgium.escaperooms.DTO.EscapeRoomRoomDTO.EscapeRoomRoomResponse;
import be.PXLResearch.code4belgium.escaperooms.domain.EscapeRoom;
import be.PXLResearch.code4belgium.escaperooms.domain.EscapeRoomRoom;
import be.PXLResearch.code4belgium.escaperooms.repository.EscapeRoomRepository;
import be.PXLResearch.code4belgium.escaperooms.repository.EscapeRoomRoomRepository;
import be.PXLResearch.code4belgium.escaperooms.service.interfaces.IEscapeRoomRoomService;
import be.PXLResearch.code4belgium.exceptions.ResourceNotFoundException;
import be.PXLResearch.code4belgium.nurseries.DTO.NurseryRoomDTO.NurseryRoomResponse;
import be.PXLResearch.code4belgium.nurseries.domain.NurseryRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EscapeRoomRoomService implements IEscapeRoomRoomService {
    private final EscapeRoomRoomRepository escapeRoomRoomRepository;
    private final EscapeRoomRepository escapeRoomRepository;

    @Override
    public List<EscapeRoomRoomResponse> getRooms() {
        List<EscapeRoomRoom> escapeRoomRooms = escapeRoomRoomRepository.findAll();
        if (escapeRoomRooms.isEmpty()) {
            return List.of();
        }

        return escapeRoomRooms.stream()
                .map(this::turnEscapeRoomRoomIntoResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<EscapeRoomRoomResponse> getRoomsForEscapeRoom(Long escapeRoomId) {
        EscapeRoom escapeRoom = escapeRoomRepository.findById(escapeRoomId).orElseThrow(() -> new ResourceNotFoundException("No escape room found with ID " + escapeRoomId));

        return escapeRoom.getRooms().stream()
                .map(this::turnEscapeRoomRoomIntoResponse)
                .collect(Collectors.toList());
    }

    @Override
    public EscapeRoomRoomResponse getRoomById(Long roomId) {
        EscapeRoomRoom escapeRoomRoom = escapeRoomRoomRepository.findById(roomId).orElseThrow(() -> new ResourceNotFoundException("No room found with ID " + roomId));

        return turnEscapeRoomRoomIntoResponse(escapeRoomRoom);
    }

    @Override
    public EscapeRoomRoom createRoom(EscapeRoomRoomRequest request) {
        EscapeRoom escapeRoom = escapeRoomRepository.findById(request.getBranchId())
                .orElseThrow(() -> new ResourceNotFoundException("No escape room found with id " + request.getBranchId()));

        EscapeRoomRoom escapeRoomRoom = EscapeRoomRoom.builder()
                .name(request.getName())
                .minimumAge(request.getMinimumAge())
                .duration(request.getDuration())
                .currentCapacity(0)
                .maxCapacity(request.getMaxCapacity())
                .branch(escapeRoom)
                .build();

        return escapeRoomRoomRepository.save(escapeRoomRoom);
    }

    private EscapeRoomRoomResponse turnEscapeRoomRoomIntoResponse(EscapeRoomRoom escapeRoomRoom) {

        return EscapeRoomRoomResponse.builder()
                .id(escapeRoomRoom.getId())
                .name(escapeRoomRoom.getName())
                .duration(escapeRoomRoom.getDuration())
                .minimumAge(escapeRoomRoom.getMinimumAge())
                .currentCapacity(escapeRoomRoom.getCurrentCapacity())
                .maxCapacity(escapeRoomRoom.getMaxCapacity())
                .build();
    }
}
