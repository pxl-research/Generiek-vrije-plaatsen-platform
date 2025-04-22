package be.PXLResearch.code4belgium.escaperooms.service;

import be.PXLResearch.code4belgium.escaperooms.DTO.EscapeRoomDto.EscapeRoomRequest;
import be.PXLResearch.code4belgium.escaperooms.DTO.EscapeRoomDto.EscapeRoomResponse;
import be.PXLResearch.code4belgium.escaperooms.domain.EscapeRoom;
import be.PXLResearch.code4belgium.escaperooms.repository.EscapeRoomRepository;
import be.PXLResearch.code4belgium.escaperooms.service.interfaces.IEscapeRoomService;
import be.PXLResearch.code4belgium.exceptions.ResourceNotFoundException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EscapeRoomService implements IEscapeRoomService {
    private final EscapeRoomRepository escapeRoomRepository;


    @Override
    public List<EscapeRoomResponse> getAllEscapeRooms() {
        List<EscapeRoom> escapeRooms = escapeRoomRepository.findAll();

        if (escapeRooms.isEmpty()) {
            throw new ResourceNotFoundException("No escape rooms found");
        }

        return escapeRooms.stream()
                .map(e -> new EscapeRoomResponse(
                        e.getId(),
                        e.getName(),
                        e.getAddress(),
                        e.getPostalCode(),
                        e.getCity(),
                        e.getEmail(),
                        e.getPhoneNumber(),
                        e.getWebsite(),
                        e.getRooms()
                        ))
                .collect(Collectors.toList());
    }

    @Override
    public EscapeRoomResponse getEscapeRoomById(Long id) {
        EscapeRoom escapeRoom = escapeRoomRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No escape room found with id " + id));

        return EscapeRoomResponse.builder()
                .id(escapeRoom.getId())
                .name(escapeRoom.getName())
                .address(escapeRoom.getAddress())
                .postalCode(escapeRoom.getPostalCode())
                .city(escapeRoom.getCity())
                .email(escapeRoom.getEmail())
                .phoneNumber(escapeRoom.getPhoneNumber())
                .website(escapeRoom.getWebsite())
                .rooms(escapeRoom.getRooms())
                .build();
    }

    @Override
    public EscapeRoomResponse addEscapeRoom(EscapeRoomRequest request) {
        return null;
    }
}
