package be.PXLResearch.code4belgium.schools.controller;

import be.PXLResearch.code4belgium.exceptions.ResourceNotFoundException;
import be.PXLResearch.code4belgium.schools.DTO.SchoolRoomDTO.SchoolRoomRequest;
import be.PXLResearch.code4belgium.schools.DTO.SchoolRoomDTO.SchoolRoomResponse;
import be.PXLResearch.code4belgium.schools.domain.SchoolRoom;
import be.PXLResearch.code4belgium.schools.service.interfaces.ISchoolRoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schools/rooms")
@RequiredArgsConstructor
public class SchoolRoomController {
    private final ISchoolRoomService schoolRoomService;

    @GetMapping
    public ResponseEntity<List<SchoolRoomResponse>> getRooms() {
        try {
            return new ResponseEntity<>(schoolRoomService.getAllSchoolRooms(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/school/{schoolId}")
    public ResponseEntity<List<SchoolRoomResponse>> getRoomsForSchool(@PathVariable Long schoolId) {
        try {
            return new ResponseEntity<>(schoolRoomService.getRoomsForSchool(schoolId), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<SchoolRoomResponse> getRoomById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(schoolRoomService.getSchoolRoomById(id), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<SchoolRoom> createRoom(@RequestBody SchoolRoomRequest roomRequest) {
        try {
            return new ResponseEntity<>(schoolRoomService.createSchoolRoom(roomRequest), HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> updateRoom(@PathVariable Long id, @RequestBody @Valid SchoolRoomRequest roomRequest) {
        try {
            schoolRoomService.updateSchoolRoom(id, roomRequest);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        try {
            schoolRoomService.deleteSchoolRoom(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
