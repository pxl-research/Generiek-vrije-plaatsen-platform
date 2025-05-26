package be.PXLResearch.code4belgium.nurseries.service;

import be.PXLResearch.code4belgium.enums.City;
import be.PXLResearch.code4belgium.exceptions.ResourceNotFoundException;
import be.PXLResearch.code4belgium.nurseries.DTO.NurseryDTO.NurseryRequest;
import be.PXLResearch.code4belgium.nurseries.DTO.NurseryDTO.NurseryResponse;
import be.PXLResearch.code4belgium.nurseries.domain.Nursery;
import be.PXLResearch.code4belgium.nurseries.domain.NurseryOrganization;
import be.PXLResearch.code4belgium.nurseries.repository.NurseryOrganizationRepository;
import be.PXLResearch.code4belgium.nurseries.repository.NurseryRepository;
import be.PXLResearch.code4belgium.nurseries.service.interfaces.INurseryService;
import be.PXLResearch.code4belgium.schools.DTO.SchoolDTO.SchoolRequest;
import be.PXLResearch.code4belgium.schools.DTO.SchoolDTO.SchoolResponse;
import be.PXLResearch.code4belgium.schools.domain.School;
import be.PXLResearch.code4belgium.schools.domain.SchoolOrganization;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NurseryService implements INurseryService {
    private final NurseryRepository nurseryRepository;
    private final ObjectMapper objectMapper;
    private final NurseryOrganizationRepository nurseryOrganizationRepository;


    @Override
    public List<NurseryResponse> getAllNurseries() {
        List<Nursery> nurseries = nurseryRepository.findAll();

        if (nurseries.isEmpty()) {
            return List.of();
        }

        return nurseries.stream()
                .map(e -> new NurseryResponse(
                        e.getId(),
                        e.getName(),
                        e.getAddress(),
                        e.getPostalCode(),
                        e.getCity(),
                        e.getEmail(),
                        e.getPhoneNumber(),
                        e.getWebsite(),
                        e.getCurrentCapacity(),
                        e.getMaxCapacity()
                        ))
                .collect(Collectors.toList());
    }

    @Override
    public NurseryResponse getNurseryById(Long id) {
        Nursery nursery = nurseryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No nursery found with id " + id));

        return NurseryResponse.builder()
                .id(nursery.getId())
                .name(nursery.getName())
                .address(nursery.getAddress())
                .postalCode(nursery.getPostalCode())
                .city(nursery.getCity())
                .email(nursery.getEmail())
                .phoneNumber(nursery.getPhoneNumber())
                .website(nursery.getWebsite())
                .currentCapacity(nursery.getCurrentCapacity())
                .maxCapacity(nursery.getMaxCapacity())
                .build();
    }

    @Override
    public Nursery createNursery(NurseryRequest request) throws IOException {
        NurseryOrganization organization = nurseryOrganizationRepository.findById(request.getOrganizationId()).orElseThrow(() -> new ResourceNotFoundException("No organization found with id " + request.getOrganizationId()));
        JsonNode node = objectMapper.readTree(request.getFilterableProperties().traverse());

        Nursery nursery = Nursery.builder()
                .name(request.getName())
                .description(request.getDescription())
                .address(request.getAddress())
                .postalCode(request.getPostalCode())
                .city(City.fromString(request.getCity()))
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .website(request.getWebsite())
                .currentCapacity(0)
                .maxCapacity(request.getMaxCapacity())
                .filterableProperties(node)
                .build();

        nurseryRepository.save(nursery);

        organization.getFreeSpots().add(nursery);
        nurseryOrganizationRepository.save(organization);

        return nursery;
    }
}
