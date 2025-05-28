package be.PXLResearch.code4belgium.escaperooms.controller;

import be.PXLResearch.code4belgium.escaperooms.DTO.EscapeRoomDto.EscapeRoomRequest;
import be.PXLResearch.code4belgium.escaperooms.DTO.EscapeRoomDto.EscapeRoomResponse;
import be.PXLResearch.code4belgium.escaperooms.domain.EscapeRoom;
import be.PXLResearch.code4belgium.escaperooms.service.EscapeRoomService;
import be.PXLResearch.code4belgium.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/escaperooms")
@RequiredArgsConstructor
public class EscapeRoomController {
    private final EscapeRoomService escapeRoomService;

    @GetMapping
    public ResponseEntity<List<EscapeRoomResponse>> getAllEscapeRooms() {
        try {
            return new ResponseEntity<>(escapeRoomService.getAllEscapeRooms(), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<EscapeRoomResponse> getEscapeRoomById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(escapeRoomService.getEscapeRoomById(id), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<EscapeRoom> createEscapeRoom(@RequestBody @Valid EscapeRoomRequest escapeRoomRequest) {
        try {
            return new ResponseEntity<>(escapeRoomService.createEscapeRoom(escapeRoomRequest), HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEscapeRoom(@PathVariable Long id) {
        try {
            escapeRoomService.deleteEscapeRoom(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 success, no body
        } catch (ResourceNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
