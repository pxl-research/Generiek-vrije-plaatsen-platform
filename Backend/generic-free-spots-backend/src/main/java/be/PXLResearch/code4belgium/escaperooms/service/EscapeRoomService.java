package be.PXLResearch.code4belgium.escaperooms.service;

import be.PXLResearch.code4belgium.enums.City;
import be.PXLResearch.code4belgium.escaperooms.DTO.EscapeRoomDTO.EscapeRoomRequest;
import be.PXLResearch.code4belgium.escaperooms.DTO.EscapeRoomDTO.EscapeRoomResponse;
import be.PXLResearch.code4belgium.escaperooms.domain.EscapeRoom;
import be.PXLResearch.code4belgium.escaperooms.domain.EscapeRoomOrganization;
import be.PXLResearch.code4belgium.escaperooms.domain.EscapeRoomRoom;
import be.PXLResearch.code4belgium.escaperooms.repository.EscapeRoomOrganizationRepository;
import be.PXLResearch.code4belgium.escaperooms.repository.EscapeRoomRepository;
import be.PXLResearch.code4belgium.escaperooms.service.interfaces.IEscapeRoomService;
import be.PXLResearch.code4belgium.exceptions.ResourceNotFoundException;
import be.PXLResearch.code4belgium.nurseries.DTO.NurseryRoomDTO.NurseryRoomResponse;
import be.PXLResearch.code4belgium.nurseries.domain.NurseryRoom;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
                .map(e -> EscapeRoomResponse.builder()
                        .id(e.getId())
                        .name(e.getName())
                        .address(e.getAddress())
                        .postalCode(e.getPostalCode())
                        .city(e.getCity())
                        .email(e.getEmail())
                        .phoneNumber(e.getPhoneNumber())
                        .website(e.getWebsite())
                        .rooms(e.getRooms())
                        .build())
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
    public EscapeRoom createEscapeRoom(EscapeRoomRequest request) throws IOException {
        List<EscapeRoomRoom> escapeRoomRooms = new ArrayList<>();
        EscapeRoomOrganization organization = escapeRoomOrganizationRepository.findById(request.getOrganizationId()).orElseThrow(() -> new ResourceNotFoundException("No organization found with id " + request.getOrganizationId()));


        EscapeRoom escapeRoom = EscapeRoom.builder()
                .name(request.getName())
                .description(request.getDescription())
                .address(request.getAddress())
                .postalCode(request.getPostalCode())
                .city(City.fromString(request.getCity()))
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .website(request.getWebsite())
                .rooms(escapeRoomRooms)
                .build();

        escapeRoomRepository.save(escapeRoom);

        organization.getBranches().add(escapeRoom);
        escapeRoomOrganizationRepository.save(organization);

        return escapeRoom;
    }

    @Override
    public void deleteEscapeRoom(Long id) {
        EscapeRoom escapeRoom = escapeRoomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Escape room with id " + id + " not found"));
        escapeRoomRepository.delete(escapeRoom);
    }

}


