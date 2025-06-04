package be.PXLResearch.code4belgium.schools.service.interfaces;

import be.PXLResearch.code4belgium.escaperooms.DTO.EscapeRoomOrganizationDTO.EscapeRoomOrganizationRequest;
import be.PXLResearch.code4belgium.escaperooms.DTO.EscapeRoomOrganizationDTO.EscapeRoomOrganizationResponse;
import be.PXLResearch.code4belgium.escaperooms.domain.EscapeRoomOrganization;
import be.PXLResearch.code4belgium.schools.DTO.SchoolOrganizationDTO.SchoolOrganizationRequest;
import be.PXLResearch.code4belgium.schools.DTO.SchoolOrganizationDTO.SchoolOrganizationResponse;
import be.PXLResearch.code4belgium.schools.domain.SchoolOrganization;

import java.util.List;

public interface ISchoolOrganizationService {
    List<SchoolOrganizationResponse> getAllSchoolOrganizations();
    SchoolOrganizationResponse getSchoolOrganizationById(Long id);
    SchoolOrganization createSchoolOrganization(SchoolOrganizationRequest request);
}
