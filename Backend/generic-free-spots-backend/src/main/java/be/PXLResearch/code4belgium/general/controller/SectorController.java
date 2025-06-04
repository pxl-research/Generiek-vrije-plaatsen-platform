package be.PXLResearch.code4belgium.general.controller;

import be.PXLResearch.code4belgium.exceptions.ResourceNotFoundException;
import be.PXLResearch.code4belgium.general.DTO.Sector.SectorRequest;
import be.PXLResearch.code4belgium.general.DTO.Sector.SectorResponse;
import be.PXLResearch.code4belgium.general.domain.Sector;
import be.PXLResearch.code4belgium.general.service.interfaces.ISectorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/sectors")
@RequiredArgsConstructor
public class SectorController {
    private final ISectorService sectorService;

    @GetMapping
    public ResponseEntity<List<SectorResponse>> getAllSectors() {
        try {
            return new ResponseEntity<>(sectorService.getAllSectors(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<SectorResponse> getSectorById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(sectorService.getSectorById(id), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Sector> createSector(@RequestBody @Valid SectorRequest sectorRequest) {
        try {
            return new ResponseEntity<>(sectorService.createSector(sectorRequest), HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
