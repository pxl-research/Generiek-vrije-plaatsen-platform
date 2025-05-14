package be.PXLResearch.code4belgium.escaperooms.controller;

import be.PXLResearch.code4belgium.escaperooms.DTO.RoomDto.RoomRequest;
import be.PXLResearch.code4belgium.escaperooms.DTO.RoomDto.RoomResponse;
import be.PXLResearch.code4belgium.escaperooms.domain.Room;
import be.PXLResearch.code4belgium.escaperooms.service.RoomService;
import be.PXLResearch.code4belgium.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @GetMapping
    public ResponseEntity<List<RoomResponse>> getRooms() {
        try {
            return new ResponseEntity<>(roomService.getRooms(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/escaperoom/{escapeRoomId}")
    public ResponseEntity<List<RoomResponse>> getRoomsForEscapeRoom(@RequestParam Long escapeRoomId) {
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
    public ResponseEntity<RoomResponse> getRoomById(@PathVariable Long id) {
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
    public ResponseEntity<Room> createRoom(@RequestBody RoomRequest roomRequest) {
        try {
            return new ResponseEntity<>(roomService.createRoom(roomRequest), HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
