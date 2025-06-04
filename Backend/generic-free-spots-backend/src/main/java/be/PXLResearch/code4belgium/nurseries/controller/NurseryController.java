package be.PXLResearch.code4belgium.nurseries.controller;

import be.PXLResearch.code4belgium.exceptions.ResourceNotFoundException;
import be.PXLResearch.code4belgium.nurseries.DTO.NurseryDTO.NurseryRequest;
import be.PXLResearch.code4belgium.nurseries.DTO.NurseryDTO.NurseryResponse;
import be.PXLResearch.code4belgium.nurseries.domain.Nursery;
import be.PXLResearch.code4belgium.nurseries.service.NurseryService;
import be.PXLResearch.code4belgium.schools.DTO.SchoolDTO.SchoolRequest;
import be.PXLResearch.code4belgium.schools.DTO.SchoolDTO.SchoolResponse;
import be.PXLResearch.code4belgium.schools.domain.School;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nurseries")
@RequiredArgsConstructor
public class NurseryController {
    private final NurseryService nurseryService;

    @GetMapping
    public ResponseEntity<List<NurseryResponse>> getAllNurseries() {
        try {
            return new ResponseEntity<>(nurseryService.getAllNurseries(), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<NurseryResponse> getSchoolById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(nurseryService.getNurseryById(id), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Nursery> createSchool(@RequestBody @Valid NurseryRequest nurseryRequest) {
        try {
            return new ResponseEntity<>(nurseryService.createNursery(nurseryRequest), HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
