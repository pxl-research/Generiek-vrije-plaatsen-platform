package be.PXLResearch.code4belgium.escaperooms.service;

import be.PXLResearch.code4belgium.enums.City;
import be.PXLResearch.code4belgium.escaperooms.DTO.EscapeRoomOrganizationDTO.EscapeRoomOrganizationRequest;
import be.PXLResearch.code4belgium.escaperooms.DTO.EscapeRoomOrganizationDTO.EscapeRoomOrganizationResponse;
import be.PXLResearch.code4belgium.escaperooms.domain.EscapeRoom;
import be.PXLResearch.code4belgium.escaperooms.domain.EscapeRoomOrganization;
import be.PXLResearch.code4belgium.escaperooms.repository.EscapeRoomOrganizationRepository;
import be.PXLResearch.code4belgium.escaperooms.repository.EscapeRoomRepository;
import be.PXLResearch.code4belgium.escaperooms.service.interfaces.IEscapeRoomOrganizationService;
import be.PXLResearch.code4belgium.exceptions.ResourceNotFoundException;
import be.PXLResearch.code4belgium.general.domain.Sector;
import be.PXLResearch.code4belgium.general.repository.SectorRepository;
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
    private final SectorRepository sectorRepository;
    private final EscapeRoomRepository escapeRoomRepository;

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
        List<EscapeRoom> escapeRooms = escapeRoomRepository.findAll();
        EscapeRoomOrganization parentOrganization = null;

        Sector sector = sectorRepository.findById(request.getSectorId()).orElseThrow(() -> new ResourceNotFoundException("No sector found with ID " + request.getSectorId()));

        if (request.getParentOrganizationId() != null) {
            parentOrganization = escapeRoomOrganizationRepository.findById(request.getParentOrganizationId()).orElseThrow(() -> new ResourceNotFoundException("No organization found with id " + request.getParentOrganizationId()));
        }

        EscapeRoomOrganization escapeRoomOrganization = EscapeRoomOrganization.builder()
                .name(request.getName())
                .parentOrganization(parentOrganization)
                .childOrganizations(request.getChildrenOrganizations().stream()
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList()))
                .freeSpots(escapeRooms)
                .sector(sector)
                .type(request.getType())
                .address(request.getAddress())
                .postalCode(request.getPostalCode())
                .city(City.fromString(request.getCity()))
                .build();

        if (!escapeRooms.isEmpty()) {
            for (EscapeRoom escapeRoom : escapeRooms) {
                escapeRoom.setOrganization(escapeRoomOrganization);
                escapeRoomRepository.save(escapeRoom);
            }
        }

        escapeRoomOrganizationRepository.save(escapeRoomOrganization);

        sector.getOrganizations().add(escapeRoomOrganization);
        sectorRepository.save(sector);

        return escapeRoomOrganization;
    }

    // Turns EscapeRoomOrganization object into EscapeRoomOrganizationResponse object
    private EscapeRoomOrganizationResponse turnEscapeRoomOrganizationToResponse(EscapeRoomOrganization organization) {
        EscapeRoomOrganization parent = new EscapeRoomOrganization();
        List<EscapeRoomOrganization> childrenOrganizations = new ArrayList<>();
        List<EscapeRoom> escapeRooms = new ArrayList<>();

        return EscapeRoomOrganizationResponse.builder()
                .id(organization.getId())
                .name(organization.getName())
                .parentOrganization(parent)
                .childrenOrganizations(childrenOrganizations)
                .escapeRooms(escapeRooms)
                .type(organization.getType())
                .address(organization.getAddress())
                .city(organization.getCity())
                .build();
    }
}
