package be.PXLResearch.code4belgium.nurseries.service;

import be.PXLResearch.code4belgium.enums.City;
import be.PXLResearch.code4belgium.exceptions.ResourceNotFoundException;
import be.PXLResearch.code4belgium.general.domain.Sector;
import be.PXLResearch.code4belgium.general.repository.SectorRepository;
import be.PXLResearch.code4belgium.nurseries.DTO.NurseryOrganizationDTO.NurseryOrganizationRequest;
import be.PXLResearch.code4belgium.nurseries.DTO.NurseryOrganizationDTO.NurseryOrganizationResponse;
import be.PXLResearch.code4belgium.nurseries.domain.Nursery;
import be.PXLResearch.code4belgium.nurseries.domain.NurseryOrganization;
import be.PXLResearch.code4belgium.nurseries.repository.NurseryOrganizationRepository;
import be.PXLResearch.code4belgium.nurseries.repository.NurseryRepository;
import be.PXLResearch.code4belgium.nurseries.service.interfaces.INurseryOrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NurseryOrganizationService implements INurseryOrganizationService {
    private final NurseryOrganizationRepository nurseryOrganizationRepository;
    private final SectorRepository sectorRepository;
    private final NurseryRepository nurseryRepository;

    @Override
    public List<NurseryOrganizationResponse> getAllNurseryOrganizations() {
        List<NurseryOrganization> nurseryOrganizations = nurseryOrganizationRepository.findAll();

        if (nurseryOrganizations.isEmpty()) {
            return List.of();
        }

        return nurseryOrganizations.stream()
                .map(this::turnNurseryOrganizationToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public NurseryOrganizationResponse getNurseryOrganizationById(Long id) {
        NurseryOrganization nurseryOrganization = nurseryOrganizationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No nursery organization found with ID " + id));

        return turnNurseryOrganizationToResponse(nurseryOrganization);
    }

    @Override
    public NurseryOrganization createNurseryOrganization(NurseryOrganizationRequest request) {
        List<Nursery> nurseries = nurseryRepository.findAll();
        NurseryOrganization parentOrganization = null;

        Sector sector = sectorRepository.findById(request.getSectorId()).orElseThrow(() -> new ResourceNotFoundException("No sector found with ID " + request.getSectorId()));

        if (request.getParentOrganizationId() != null) {
            parentOrganization = nurseryOrganizationRepository.findById(request.getParentOrganizationId()).orElseThrow(() -> new ResourceNotFoundException("No organization found with id " + request.getParentOrganizationId()));
        }

        NurseryOrganization nurseryOrganization = NurseryOrganization.builder()
                .name(request.getName())
                .parentOrganization(parentOrganization)
                .childOrganizations(request.getChildrenOrganizations().stream()
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList()))
                .freeSpots(nurseries)
                .sector(sector)
                .type(request.getType())
                .address(request.getAddress())
                .postalCode(request.getPostalCode())
                .city(City.fromString(request.getCity()))
                .build();

        if (!nurseries.isEmpty()) {
            for (Nursery nursery : nurseries) {
                nursery.setOrganization(nurseryOrganization);
                nurseryRepository.save(nursery);
            }
        }

        nurseryOrganizationRepository.save(nurseryOrganization);

        sector.getOrganizations().add(nurseryOrganization);
        sectorRepository.save(sector);

        return nurseryOrganization;
    }

    // Turns SchoolOrganization object into SchoolOrganizationResponse object
    private NurseryOrganizationResponse turnNurseryOrganizationToResponse(NurseryOrganization organization) {
        NurseryOrganization parent = new NurseryOrganization();
        List<NurseryOrganization> childrenOrganizations = new ArrayList<>();
        List<Nursery> nurseries = new ArrayList<>();

        return NurseryOrganizationResponse.builder()
                .id(organization.getId())
                .name(organization.getName())
                .parentOrganization(parent)
                .childrenOrganizations(childrenOrganizations)
                .nurseries(nurseries)
                .type(organization.getType())
                .address(organization.getAddress())
                .city(organization.getCity())
                .build();
    }
}
