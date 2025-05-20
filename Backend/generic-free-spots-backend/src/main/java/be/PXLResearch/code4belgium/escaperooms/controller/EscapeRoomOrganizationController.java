package be.PXLResearch.code4belgium.escaperooms.controller;

import be.PXLResearch.code4belgium.escaperooms.DTO.EscapeRoomOrganizationDTO.EscapeRoomOrganizationRequest;
import be.PXLResearch.code4belgium.escaperooms.DTO.EscapeRoomOrganizationDTO.EscapeRoomOrganizationResponse;
import be.PXLResearch.code4belgium.escaperooms.domain.EscapeRoomOrganization;
import be.PXLResearch.code4belgium.escaperooms.service.EscapeRoomOrganizationService;
import be.PXLResearch.code4belgium.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/escaperooms/organizations")
@RequiredArgsConstructor
public class EscapeRoomOrganizationController {
    private final EscapeRoomOrganizationService escapeRoomOrganizationService;

    @GetMapping
    public ResponseEntity<List<EscapeRoomOrganizationResponse>> getAllEscapeRoomOrganizations() {
        try {
            return new ResponseEntity<>(escapeRoomOrganizationService.getAllEscapeRoomOrganizations(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<EscapeRoomOrganizationResponse> getEscapeRoomOrganizationById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(escapeRoomOrganizationService.getEscapeRoomOrganizationById(id), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<EscapeRoomOrganization> createEscapeRoomOrganization(@RequestBody @Valid EscapeRoomOrganizationRequest request) {
        try {
            return new ResponseEntity<>(escapeRoomOrganizationService.createEscapeRoomOrganization(request), HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
