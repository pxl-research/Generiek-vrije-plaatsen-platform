package be.PXLResearch.code4belgium.escaperooms.service;

import be.PXLResearch.code4belgium.escaperooms.DTO.EscapeRoomDto.EscapeRoomResponse;
import be.PXLResearch.code4belgium.escaperooms.DTO.EscapeRoomOrganizationDTO.EscapeRoomOrganizationRequest;
import be.PXLResearch.code4belgium.escaperooms.DTO.EscapeRoomOrganizationDTO.EscapeRoomOrganizationResponse;
import be.PXLResearch.code4belgium.escaperooms.domain.EscapeRoom;
import be.PXLResearch.code4belgium.escaperooms.domain.EscapeRoomOrganization;
import be.PXLResearch.code4belgium.escaperooms.repository.EscapeRoomOrganizationRepository;
import be.PXLResearch.code4belgium.escaperooms.service.interfaces.IEscapeRoomOrganizationService;
import be.PXLResearch.code4belgium.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EscapeRoomOrganizationService implements IEscapeRoomOrganizationService {
    private final EscapeRoomOrganizationRepository escapeRoomOrganizationRepository;

    @Override
    public List<EscapeRoomOrganizationResponse> getAllEscapeRoomOrganizations() {
        List<EscapeRoomOrganization> escapeRoomOrganizations = escapeRoomOrganizationRepository.findAll();

        if (escapeRoomOrganizations.isEmpty()) {
            return List.of();
        }

        return escapeRoomOrganizations.stream()
                .map(this::turnEscapeRoomOrganizationToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public EscapeRoomOrganizationResponse getEscapeRoomOrganizationById(Long id) {
        EscapeRoomOrganization escapeRoomOrganization = escapeRoomOrganizationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No escape room organization found with ID " + id));

        return turnEscapeRoomOrganizationToResponse(escapeRoomOrganization);
    }

    @Override
    public EscapeRoomOrganization createEscapeRoomOrganization(EscapeRoomOrganizationRequest request) {
        List<EscapeRoomOrganization> childrenOrganizations = new ArrayList<>();
        List<EscapeRoom> escapeRooms = new ArrayList<>();

        EscapeRoomOrganization escapeRoomOrganization = EscapeRoomOrganization.builder()
                .name(request.getName())
                .parentOrganization(request.getParentOrganization())
                .childOrganizations(request.getChildrenOrganizations().stream()
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList()))
                .freeSpots(request.getEscapeRooms())
                .sector(request.getSector())
                .type(request.getType())
                .address(request.getAddress())
                .postalCode(request.getPostalCode())
                .city(request.getCity())
                .build();

        return escapeRoomOrganizationRepository.save(escapeRoomOrganization);
    }

    // Turns EscapeRoomOrganization object into EscapeRoomOrganizationResponse object
    private EscapeRoomOrganizationResponse turnEscapeRoomOrganizationToResponse(EscapeRoomOrganization organization) {
        return EscapeRoomOrganizationResponse.builder()
                .id(organization.getId())
                .name(organization.getName())
                .parentOrganization((EscapeRoomOrganization) organization.getParentOrganization())
                .childrenOrganizations(organization.getChildOrganizations().stream()
                        .filter(org -> org instanceof EscapeRoomOrganization)
                        .map(org -> (EscapeRoomOrganization) org)
                        .collect(Collectors.toList()))
                .escapeRooms(organization.getFreeSpots())
                .sector(organization.getSector())
                .type(organization.getType())
                .address(organization.getAddress())
                .city(organization.getCity())
                .build();
    }
}
