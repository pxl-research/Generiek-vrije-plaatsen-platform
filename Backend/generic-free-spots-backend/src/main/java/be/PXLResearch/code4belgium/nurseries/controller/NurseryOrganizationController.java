package be.PXLResearch.code4belgium.nurseries.controller;

import be.PXLResearch.code4belgium.exceptions.ResourceNotFoundException;
import be.PXLResearch.code4belgium.nurseries.DTO.NurseryOrganizationDTO.NurseryOrganizationRequest;
import be.PXLResearch.code4belgium.nurseries.DTO.NurseryOrganizationDTO.NurseryOrganizationResponse;
import be.PXLResearch.code4belgium.nurseries.domain.NurseryOrganization;
import be.PXLResearch.code4belgium.nurseries.service.NurseryOrganizationService;
import be.PXLResearch.code4belgium.nurseries.service.interfaces.INurseryOrganizationService;
import be.PXLResearch.code4belgium.schools.DTO.SchoolOrganizationDTO.SchoolOrganizationRequest;
import be.PXLResearch.code4belgium.schools.DTO.SchoolOrganizationDTO.SchoolOrganizationResponse;
import be.PXLResearch.code4belgium.schools.domain.SchoolOrganization;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizations/nurseries")
@RequiredArgsConstructor
public class NurseryOrganizationController {
    private final INurseryOrganizationService nurseryOrganizationService;

    @GetMapping
    public ResponseEntity<List<NurseryOrganizationResponse>> getAllNurseryOrganizations() {
        try {
            return new ResponseEntity<>(nurseryOrganizationService.getAllNurseryOrganizations(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<NurseryOrganizationResponse> getSchoolOrganizationById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(nurseryOrganizationService.getNurseryOrganizationById(id), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<NurseryOrganization> createSchoolOrganization(@RequestBody @Valid NurseryOrganizationRequest request) {
        try {
            return new ResponseEntity<>(nurseryOrganizationService.createNurseryOrganization(request), HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
