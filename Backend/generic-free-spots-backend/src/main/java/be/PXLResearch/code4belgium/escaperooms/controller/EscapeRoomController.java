package be.PXLResearch.code4belgium.escaperooms.controller;

import be.PXLResearch.code4belgium.escaperooms.DTO.EscapeRoomDto.EscapeRoomResponse;
import be.PXLResearch.code4belgium.escaperooms.service.EscapeRoomService;
import be.PXLResearch.code4belgium.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        }
    }
}
