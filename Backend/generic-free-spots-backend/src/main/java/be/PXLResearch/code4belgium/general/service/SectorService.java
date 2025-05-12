package be.PXLResearch.code4belgium.general.service;

import be.PXLResearch.code4belgium.general.DTO.SectorRequest;
import be.PXLResearch.code4belgium.general.DTO.SectorResponse;
import be.PXLResearch.code4belgium.general.Sector;
import be.PXLResearch.code4belgium.general.interfaces.IOrganization;
import be.PXLResearch.code4belgium.general.service.interfaces.ISectorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectorService implements ISectorService {
    @Override
    public List<SectorResponse> getAllSectors() {
        return List.of();
    }

    @Override
    public SectorResponse getSectorById(Long id) {
        return null;
    }

    @Override
    public <T extends IOrganization, U> Sector<T, U> createSector(SectorRequest request) {
        return null;
    }
}
