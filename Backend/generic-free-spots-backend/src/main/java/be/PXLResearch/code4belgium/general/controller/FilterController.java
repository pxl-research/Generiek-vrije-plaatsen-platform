package be.PXLResearch.code4belgium.general.controller;

import be.PXLResearch.code4belgium.exceptions.ResourceNotFoundException;
import be.PXLResearch.code4belgium.general.DTO.Filter.FilterRequest;
import be.PXLResearch.code4belgium.general.DTO.Filter.FilterResponse;
import be.PXLResearch.code4belgium.general.Filter;
import be.PXLResearch.code4belgium.general.service.FilterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/filters")
@RequiredArgsConstructor
public class FilterController {
    private final FilterService filterService;

    @GetMapping
    public ResponseEntity<List<FilterResponse>> getAllFilters() {
        try {
            return new ResponseEntity<>(filterService.getAllFilters(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<FilterResponse> getFilterById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(filterService.getFilterById(id), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Filter> createFilter(@RequestBody @Valid FilterRequest filterRequest) {
        try {
            return new ResponseEntity<>(filterService.createFilter(filterRequest), HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


}
