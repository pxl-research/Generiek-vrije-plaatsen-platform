package be.PXLResearch.code4belgium.escaperooms.controller;

import be.PXLResearch.code4belgium.escaperooms.DTO.EscapeRoomRoomDTO.EscapeRoomRoomRequest;
import be.PXLResearch.code4belgium.escaperooms.DTO.EscapeRoomRoomDTO.EscapeRoomRoomResponse;
import be.PXLResearch.code4belgium.escaperooms.domain.EscapeRoomRoom;
import be.PXLResearch.code4belgium.escaperooms.service.interfaces.IEscapeRoomRoomService;
import be.PXLResearch.code4belgium.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/escaperooms/rooms")
@RequiredArgsConstructor
public class EscapeRoomRoomController {
    private final IEscapeRoomRoomService roomService;

    @GetMapping
    public ResponseEntity<List<EscapeRoomRoomResponse>> getRooms() {
        try {
            return new ResponseEntity<>(roomService.getRooms(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/escaperoom/{escapeRoomId}")
    public ResponseEntity<List<EscapeRoomRoomResponse>> getRoomsForEscapeRoom(@PathVariable Long escapeRoomId) {
        try {
            return new ResponseEntity<>(roomService.getRoomsForEscapeRoom(escapeRoomId), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<EscapeRoomRoomResponse> getRoomById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(roomService.getRoomById(id), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<EscapeRoomRoom> createRoom(@RequestBody EscapeRoomRoomRequest escapeRoomRoomRequest) {
        try {
            return new ResponseEntity<>(roomService.createRoom(escapeRoomRoomRequest), HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
