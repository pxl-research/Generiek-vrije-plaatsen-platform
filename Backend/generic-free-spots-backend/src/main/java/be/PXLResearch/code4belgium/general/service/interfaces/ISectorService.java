package be.PXLResearch.code4belgium.general.service.interfaces;


import be.PXLResearch.code4belgium.general.DTO.SectorRequest;
import be.PXLResearch.code4belgium.general.DTO.SectorResponse;
import be.PXLResearch.code4belgium.general.Organization;
import be.PXLResearch.code4belgium.general.Sector;
import be.PXLResearch.code4belgium.general.interfaces.IOrganization;

import java.util.List;

public interface ISectorService {
    List<SectorResponse> getAllSectors();
    SectorResponse getSectorById(Long id);
    <T extends IOrganization, U> Sector<T, U> createSector(SectorRequest request);
}
