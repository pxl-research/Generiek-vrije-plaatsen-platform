package be.PXLResearch.code4belgium.nurseries.service.interfaces;


import be.PXLResearch.code4belgium.nurseries.DTO.NurseryDTO.NurseryRequest;
import be.PXLResearch.code4belgium.nurseries.DTO.NurseryDTO.NurseryResponse;
import be.PXLResearch.code4belgium.nurseries.domain.Nursery;
import be.PXLResearch.code4belgium.schools.DTO.SchoolDTO.SchoolRequest;
import be.PXLResearch.code4belgium.schools.DTO.SchoolDTO.SchoolResponse;
import be.PXLResearch.code4belgium.schools.domain.School;

import java.io.IOException;
import java.util.List;

public interface INurseryService {
    List<NurseryResponse> getAllNurseries();
    NurseryResponse getNurseryById(Long id);
    Nursery createNursery(NurseryRequest request) throws IOException;
}
