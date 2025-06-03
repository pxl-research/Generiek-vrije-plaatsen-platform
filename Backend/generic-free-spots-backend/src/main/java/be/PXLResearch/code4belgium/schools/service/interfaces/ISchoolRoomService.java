package be.PXLResearch.code4belgium.schools.service.interfaces;

import be.PXLResearch.code4belgium.schools.DTO.SchoolRoomDTO.SchoolRoomRequest;
import be.PXLResearch.code4belgium.schools.DTO.SchoolRoomDTO.SchoolRoomResponse;
import be.PXLResearch.code4belgium.schools.domain.SchoolRoom;

import java.util.List;

public interface ISchoolRoomService {
    List<SchoolRoomResponse> getAllSchoolRooms();
    List<SchoolRoomResponse> getRoomsForSchool(Long schoolId);
    SchoolRoomResponse getSchoolRoomById(Long id);
    SchoolRoom createSchoolRoom(SchoolRoomRequest request);
    void updateSchoolRoom(Long id, SchoolRoomRequest request);
    void deleteSchoolRoom(Long id);
}
