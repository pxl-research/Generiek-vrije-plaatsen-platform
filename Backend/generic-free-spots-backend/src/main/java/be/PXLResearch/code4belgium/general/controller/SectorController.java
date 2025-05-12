package be.PXLResearch.code4belgium.general.controller;

import be.PXLResearch.code4belgium.general.service.SectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/sectors")
@RequiredArgsConstructor
public class SectorController {
    private final SectorService sectorService;

}
