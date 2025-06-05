package be.PXLResearch.code4belgium.general.service;

import be.PXLResearch.code4belgium.exceptions.ResourceNotFoundException;
import be.PXLResearch.code4belgium.general.DTO.SectorDTO.SectorRequest;
import be.PXLResearch.code4belgium.general.DTO.SectorDTO.SectorResponse;
import be.PXLResearch.code4belgium.general.domain.Filter;
import be.PXLResearch.code4belgium.general.domain.Organization;
import be.PXLResearch.code4belgium.general.domain.Sector;
import be.PXLResearch.code4belgium.general.repository.SectorRepository;
import be.PXLResearch.code4belgium.general.service.interfaces.ISectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SectorService implements ISectorService {
    private final SectorRepository sectorRepository;

    @Override
    public List<SectorResponse> getAllSectors() {
        List<Sector> sectors = sectorRepository.findAll();

        if (sectors.isEmpty()) {
            return List.of();
        }

        return sectors.stream()
                .map(s -> new SectorResponse(
                        s.getId(),
                        s.getName(),
                        s.getImage(),
                        s.getOrganizations(),
                        s.getFilters()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public SectorResponse getSectorById(Long id) {
        Sector sector = sectorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No sector found with ID " + id));

        return SectorResponse.builder()
                .id(sector.getId())
                .name(sector.getName())
                .image(sector.getImage())
                .organizations(sector.getOrganizations())
                .filters(sector.getFilters())
                .build();
    }

    @Override
    public Sector createSector(SectorRequest request) {
        List<Organization<?>> organizations = new ArrayList<>();
        List<Filter> filters = new ArrayList<>();

        Sector sector = Sector.builder()
                .name(request.getName())
                .image(request.getImage())
                .organizations(organizations)
                .filters(filters)
                .build();

        return sectorRepository.save(sector);
    }
}
