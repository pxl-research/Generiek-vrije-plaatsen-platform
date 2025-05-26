package be.PXLResearch.code4belgium.schools.service.interfaces;


import be.PXLResearch.code4belgium.escaperooms.DTO.EscapeRoomDto.EscapeRoomRequest;
import be.PXLResearch.code4belgium.escaperooms.DTO.EscapeRoomDto.EscapeRoomResponse;
import be.PXLResearch.code4belgium.escaperooms.domain.EscapeRoom;
import be.PXLResearch.code4belgium.schools.DTO.SchoolDTO.SchoolRequest;
import be.PXLResearch.code4belgium.schools.DTO.SchoolDTO.SchoolResponse;
import be.PXLResearch.code4belgium.schools.domain.School;

import java.io.IOException;
import java.util.List;

public interface ISchoolService {
    List<SchoolResponse> getAllSchools();
    SchoolResponse getSchoolById(Long id);
    School createSchool(SchoolRequest request) throws IOException;
}
