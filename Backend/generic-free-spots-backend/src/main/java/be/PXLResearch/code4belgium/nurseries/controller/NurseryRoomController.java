package be.PXLResearch.code4belgium.nurseries.controller;

import be.PXLResearch.code4belgium.exceptions.ResourceNotFoundException;
import be.PXLResearch.code4belgium.nurseries.DTO.NurseryRoomDTO.NurseryRoomRequest;
import be.PXLResearch.code4belgium.nurseries.DTO.NurseryRoomDTO.NurseryRoomResponse;
import be.PXLResearch.code4belgium.nurseries.domain.NurseryRoom;
import be.PXLResearch.code4belgium.nurseries.service.interfaces.INurseryRoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nurseries/rooms")
@RequiredArgsConstructor
public class NurseryRoomController {
    private final INurseryRoomService nurseryRoomService;

    @GetMapping
    public ResponseEntity<List<NurseryRoomResponse>> getRooms() {
        try {
            return new ResponseEntity<>(nurseryRoomService.getAllNurseryRooms(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/nursery/{nurseryId}")
    public ResponseEntity<List<NurseryRoomResponse>> getRoomsForNursery(@PathVariable Long nurseryId) {
        try {
            return new ResponseEntity<>(nurseryRoomService.getRoomsForNursery(nurseryId), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<NurseryRoomResponse> getRoomById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(nurseryRoomService.getNurseryRoomById(id), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<NurseryRoom> createRoom(@RequestBody NurseryRoomRequest roomRequest) {
        try {
            return new ResponseEntity<>(nurseryRoomService.createNurseryRoom(roomRequest), HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> updateRoom(@PathVariable Long id, @RequestBody @Valid NurseryRoomRequest roomRequest) {
        try {
            nurseryRoomService.updateNurseryRoom(id, roomRequest);
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
            nurseryRoomService.deleteNurseryRoom(id);
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
