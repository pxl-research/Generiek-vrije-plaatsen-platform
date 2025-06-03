package be.PXLResearch.code4belgium.schools.service;

import be.PXLResearch.code4belgium.exceptions.ResourceNotFoundException;
import be.PXLResearch.code4belgium.schools.DTO.SchoolRoomDTO.SchoolRoomRequest;
import be.PXLResearch.code4belgium.schools.DTO.SchoolRoomDTO.SchoolRoomResponse;
import be.PXLResearch.code4belgium.schools.domain.School;
import be.PXLResearch.code4belgium.schools.domain.SchoolRoom;
import be.PXLResearch.code4belgium.schools.repository.SchoolRepository;
import be.PXLResearch.code4belgium.schools.repository.SchoolRoomRepository;
import be.PXLResearch.code4belgium.schools.service.interfaces.ISchoolRoomService;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SchoolRoomService implements ISchoolRoomService {
    private final SchoolRoomRepository schoolRoomRepository;
    private final SchoolRepository schoolRepository;

    @Override
    public List<SchoolRoomResponse> getAllSchoolRooms() {
        List<SchoolRoom> schoolRooms = schoolRoomRepository.findAll();

        if (schoolRooms.isEmpty()) {
            return List.of();
        }

        return schoolRooms.stream()
                .map(this::turnSchoolRoomIntoResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<SchoolRoomResponse> getRoomsForSchool(Long schoolId) {
        School school = schoolRepository.findById(schoolId).orElseThrow(() -> new ResourceNotFoundException("School not found"));

        return school.getSchoolRooms().stream()
                .map(this::turnSchoolRoomIntoResponse)
                .collect(Collectors.toList());
    }

    @Override
    public SchoolRoomResponse getSchoolRoomById(Long id) {
        SchoolRoom room = schoolRoomRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("SchoolRoom not found"));

        return turnSchoolRoomIntoResponse(room);
    }

    @Override
    public SchoolRoom createSchoolRoom(SchoolRoomRequest request) {
        School school = schoolRepository.findById(request.getSchoolId()).orElseThrow(() -> new ResourceNotFoundException("School not found"));

        SchoolRoom schoolRoom = SchoolRoom.builder()
                .name(request.getName())
                .minimumAge(request.getMinimumAge())
                .duration(request.getDuration())
                .minStudents(request.getMinStudents())
                .maxStudents(request.getMaxStudents())
                .school(school)
                .build();

        return schoolRoomRepository.save(schoolRoom);
    }

    @Override
    public void updateSchoolRoom(Long id, SchoolRoomRequest request) {
        SchoolRoom schoolRoom = schoolRoomRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("SchoolRoom not found"));

        schoolRoom.setName(request.getName());
        schoolRoom.setMinimumAge(request.getMinimumAge());
        schoolRoom.setDuration(request.getDuration());
        schoolRoom.setMinStudents(request.getMinStudents());
        schoolRoom.setMaxStudents(request.getMaxStudents());
        schoolRoomRepository.save(schoolRoom);
    }

    @Override
    public void deleteSchoolRoom(Long id) {
        SchoolRoom room = schoolRoomRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("SchoolRoom not found"));
        schoolRoomRepository.delete(room);
    }

    private SchoolRoomResponse turnSchoolRoomIntoResponse(SchoolRoom schoolRoom) {

        return SchoolRoomResponse.builder()
                .id(schoolRoom.getId())
                .name(schoolRoom.getName())
                .minimumAge(schoolRoom.getMinimumAge())
                .minStudents(schoolRoom.getMinStudents())
                .maxStudents(schoolRoom.getMaxStudents())
                .build();
    }
}
