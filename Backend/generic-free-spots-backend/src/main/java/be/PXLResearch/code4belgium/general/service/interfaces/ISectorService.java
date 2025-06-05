package be.PXLResearch.code4belgium.general.service.interfaces;


import be.PXLResearch.code4belgium.general.DTO.SectorDTO.SectorRequest;
import be.PXLResearch.code4belgium.general.DTO.SectorDTO.SectorResponse;
import be.PXLResearch.code4belgium.general.domain.Sector;

import java.util.List;

public interface ISectorService {
    List<SectorResponse> getAllSectors();
    SectorResponse getSectorById(Long id);
    Sector createSector(SectorRequest request);
}
