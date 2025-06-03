package be.PXLResearch.code4belgium.nurseries.service.interfaces;

import be.PXLResearch.code4belgium.nurseries.DTO.NurseryRoomDTO.NurseryRoomRequest;
import be.PXLResearch.code4belgium.nurseries.DTO.NurseryRoomDTO.NurseryRoomResponse;
import be.PXLResearch.code4belgium.nurseries.domain.NurseryRoom;
import be.PXLResearch.code4belgium.schools.DTO.SchoolRoomDTO.SchoolRoomRequest;
import be.PXLResearch.code4belgium.schools.DTO.SchoolRoomDTO.SchoolRoomResponse;
import be.PXLResearch.code4belgium.schools.domain.SchoolRoom;

import java.util.List;

public interface INurseryRoomService {
    List<NurseryRoomResponse> getAllNurseryRooms();
    List<NurseryRoomResponse> getRoomsForNursery(Long nurseryId);
    NurseryRoomResponse getNurseryRoomById(Long id);
    NurseryRoom createNurseryRoom(NurseryRoomRequest request);
    void updateNurseryRoom(Long id, NurseryRoomRequest request);
    void deleteNurseryRoom(Long id);
}
