package be.PXLResearch.code4belgium.escaperooms.service.interfaces;

import be.PXLResearch.code4belgium.escaperooms.DTO.EscapeRoomOrganizationDTO.EscapeRoomOrganizationRequest;
import be.PXLResearch.code4belgium.escaperooms.DTO.EscapeRoomOrganizationDTO.EscapeRoomOrganizationResponse;
import be.PXLResearch.code4belgium.escaperooms.domain.EscapeRoomOrganization;

import java.util.List;

public interface IEscapeRoomOrganizationService {
    List<EscapeRoomOrganizationResponse> getAllEscapeRoomOrganizations();
    EscapeRoomOrganizationResponse getEscapeRoomOrganizationById(Long id);
    EscapeRoomOrganization createEscapeRoomOrganization(EscapeRoomOrganizationRequest request);
}
