package be.PXLResearch.code4belgium.escaperooms.service;

import be.PXLResearch.code4belgium.enums.City;
import be.PXLResearch.code4belgium.escaperooms.DTO.EscapeRoomDto.EscapeRoomRequest;
import be.PXLResearch.code4belgium.escaperooms.DTO.EscapeRoomDto.EscapeRoomResponse;
import be.PXLResearch.code4belgium.escaperooms.domain.EscapeRoom;
import be.PXLResearch.code4belgium.escaperooms.domain.EscapeRoomOrganization;
import be.PXLResearch.code4belgium.escaperooms.domain.Room;
import be.PXLResearch.code4belgium.escaperooms.repository.EscapeRoomOrganizationRepository;
import be.PXLResearch.code4belgium.escaperooms.repository.EscapeRoomRepository;
import be.PXLResearch.code4belgium.escaperooms.service.interfaces.IEscapeRoomService;
import be.PXLResearch.code4belgium.exceptions.ResourceNotFoundException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EscapeRoomService implements IEscapeRoomService {
    private final EscapeRoomRepository escapeRoomRepository;
    private final ObjectMapper objectMapper;
    private final EscapeRoomOrganizationRepository escapeRoomOrganizationRepository;


    @Override
    public List<EscapeRoomResponse> getAllEscapeRooms() {
        List<EscapeRoom> escapeRooms = escapeRoomRepository.findAll();

        if (escapeRooms.isEmpty()) {
            return List.of();
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
                        e.getCurrentCapacity(),
                        e.getMaxCapacity(),
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
                .currentCapacity(escapeRoom.getCurrentCapacity())
                .maxCapacity(escapeRoom.getMaxCapacity())
                .rooms(escapeRoom.getRooms())
                .build();
    }

    @Override
    public EscapeRoom createEscapeRoom(EscapeRoomRequest request) throws IOException {
        List<Room> rooms = new ArrayList<>();
        EscapeRoomOrganization organization = escapeRoomOrganizationRepository.findById(request.getOrganizationId()).orElseThrow(() -> new ResourceNotFoundException("No organization found with id " + request.getOrganizationId()));
        ObjectNode rootNode = objectMapper.createObjectNode();
        ArrayNode appliedFilters = objectMapper.createArrayNode();

        rootNode.set("appliedFilters", appliedFilters);


        EscapeRoom escapeRoom = EscapeRoom.builder()
                .name(request.getName())
                .description(request.getDescription())
                //.createdAt(LocalDateTime.now())
                //.updatedAt(LocalDateTime.now())
                .address(request.getAddress())
                .postalCode(request.getPostalCode())
                .city(City.fromString(request.getCity()))
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .website(request.getWebsite())
                .currentCapacity(0)
                .maxCapacity(request.getMaxCapacity())
                .rooms(rooms)
                .filterableProperties(rootNode)
                .build();

        escapeRoomRepository.save(escapeRoom);

        organization.getFreeSpots().add(escapeRoom);
        escapeRoomOrganizationRepository.save(organization);

        return escapeRoom;
    }
}

public void deleteEscapeRoom(Long id) {
    EscapeRoom escapeRoom = escapeRoomRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Escape room with id " + id + " not found"));
    escapeRoomRepository.delete(escapeRoom);
}

