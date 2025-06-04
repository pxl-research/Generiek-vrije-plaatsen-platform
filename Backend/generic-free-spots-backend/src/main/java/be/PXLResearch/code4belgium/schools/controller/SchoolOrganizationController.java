package be.PXLResearch.code4belgium.schools.controller;

import be.PXLResearch.code4belgium.escaperooms.DTO.EscapeRoomOrganizationDTO.EscapeRoomOrganizationRequest;
import be.PXLResearch.code4belgium.escaperooms.DTO.EscapeRoomOrganizationDTO.EscapeRoomOrganizationResponse;
import be.PXLResearch.code4belgium.escaperooms.domain.EscapeRoomOrganization;
import be.PXLResearch.code4belgium.exceptions.ResourceNotFoundException;
import be.PXLResearch.code4belgium.schools.DTO.SchoolOrganizationDTO.SchoolOrganizationRequest;
import be.PXLResearch.code4belgium.schools.DTO.SchoolOrganizationDTO.SchoolOrganizationResponse;
import be.PXLResearch.code4belgium.schools.domain.SchoolOrganization;
import be.PXLResearch.code4belgium.schools.service.SchoolOrganizationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizations/schools")
@RequiredArgsConstructor
public class SchoolOrganizationController {
    private final SchoolOrganizationService schoolOrganizationService;

    @GetMapping
    public ResponseEntity<List<SchoolOrganizationResponse>> getAllSchoolOrganizations() {
        try {
            return new ResponseEntity<>(schoolOrganizationService.getAllSchoolOrganizations(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<SchoolOrganizationResponse> getSchoolOrganizationById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(schoolOrganizationService.getSchoolOrganizationById(id), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<SchoolOrganization> createSchoolOrganization(@RequestBody @Valid SchoolOrganizationRequest request) {
        try {
            return new ResponseEntity<>(schoolOrganizationService.createSchoolOrganization(request), HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
