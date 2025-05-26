package be.PXLResearch.code4belgium.nurseries.service.interfaces;

import be.PXLResearch.code4belgium.nurseries.DTO.NurseryOrganizationDTO.NurseryOrganizationRequest;
import be.PXLResearch.code4belgium.nurseries.DTO.NurseryOrganizationDTO.NurseryOrganizationResponse;
import be.PXLResearch.code4belgium.nurseries.domain.NurseryOrganization;
import be.PXLResearch.code4belgium.schools.DTO.SchoolOrganizationDTO.SchoolOrganizationRequest;
import be.PXLResearch.code4belgium.schools.DTO.SchoolOrganizationDTO.SchoolOrganizationResponse;
import be.PXLResearch.code4belgium.schools.domain.SchoolOrganization;

import java.util.List;

public interface INurseryOrganizationService {
    List<NurseryOrganizationResponse> getAllNurseryOrganizations();
    NurseryOrganizationResponse getNurseryOrganizationById(Long id);
    NurseryOrganization createNurseryOrganization(NurseryOrganizationRequest request);
}
