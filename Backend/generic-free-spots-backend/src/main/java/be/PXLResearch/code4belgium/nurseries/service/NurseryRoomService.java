package be.PXLResearch.code4belgium.nurseries.service;

import be.PXLResearch.code4belgium.exceptions.ResourceNotFoundException;
import be.PXLResearch.code4belgium.nurseries.DTO.NurseryRoomDTO.NurseryRoomRequest;
import be.PXLResearch.code4belgium.nurseries.DTO.NurseryRoomDTO.NurseryRoomResponse;
import be.PXLResearch.code4belgium.nurseries.domain.Nursery;
import be.PXLResearch.code4belgium.nurseries.domain.NurseryRoom;
import be.PXLResearch.code4belgium.nurseries.repository.NurseryRepository;
import be.PXLResearch.code4belgium.nurseries.repository.NurseryRoomRepository;
import be.PXLResearch.code4belgium.nurseries.service.interfaces.INurseryRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NurseryRoomService implements INurseryRoomService {
    private final NurseryRoomRepository nurseryRoomRepository;
    private final NurseryRepository nurseryRepository;

    @Override
    public List<NurseryRoomResponse> getAllNurseryRooms() {
        List<NurseryRoom> nurseryRooms = nurseryRoomRepository.findAll();

        if (nurseryRooms.isEmpty()) {
            return List.of();
        }

        return nurseryRooms.stream()
                .map(this::turnNurseryRoomIntoResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<NurseryRoomResponse> getRoomsForNursery(Long nurseryId) {
        Nursery nursery = nurseryRepository.findById(nurseryId).orElseThrow(() -> new ResourceNotFoundException("Nursery not found"));

        return nursery.getNurseryRooms().stream()
                .map(this::turnNurseryRoomIntoResponse)
                .collect(Collectors.toList());
    }

    @Override
    public NurseryRoomResponse getNurseryRoomById(Long id) {
        NurseryRoom room = nurseryRoomRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("NurseryRoom not found"));

        return turnNurseryRoomIntoResponse(room);
    }

    @Override
    public NurseryRoom createNurseryRoom(NurseryRoomRequest request) {
        Nursery nursery = nurseryRepository.findById(request.getNurseryId())
                .orElseThrow(() -> new ResourceNotFoundException("Nursery not found"));

        NurseryRoom nurseryRoom = NurseryRoom.builder()
                .name(request.getName())
                .minimumAge(request.getMinimumAge())
                .duration(request.getDuration())
                .minKids(request.getMinKids())
                .maxKids(request.getMaxKids())
                .nursery(nursery)
                .build();

        return nurseryRoomRepository.save(nurseryRoom);
    }

    @Override
    public void updateNurseryRoom(Long id, NurseryRoomRequest request) {
        NurseryRoom nurseryRoom = nurseryRoomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NurseryRoom not found"));

        nurseryRoom.setName(request.getName());
        nurseryRoom.setMinimumAge(request.getMinimumAge());
        nurseryRoom.setDuration(request.getDuration());
        nurseryRoom.setMinKids(request.getMinKids());
        nurseryRoom.setMaxKids(request.getMaxKids());

        nurseryRoomRepository.save(nurseryRoom);
    }

    @Override
    public void deleteNurseryRoom(Long id) {
        NurseryRoom room = nurseryRoomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NurseryRoom not found"));
        nurseryRoomRepository.delete(room);
    }

    private NurseryRoomResponse turnNurseryRoomIntoResponse(NurseryRoom nurseryRoom) {

        return NurseryRoomResponse.builder()
                .id(nurseryRoom.getId())
                .name(nurseryRoom.getName())
                .minimumAge(nurseryRoom.getMinimumAge())
                .minKids(nurseryRoom.getMinKids())
                .maxKids(nurseryRoom.getMaxKids())
                .build();
    }
}
