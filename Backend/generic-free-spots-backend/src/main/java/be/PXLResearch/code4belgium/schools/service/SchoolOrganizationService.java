package be.PXLResearch.code4belgium.schools.service;

import be.PXLResearch.code4belgium.enums.City;
import be.PXLResearch.code4belgium.exceptions.ResourceNotFoundException;
import be.PXLResearch.code4belgium.general.domain.Organization;
import be.PXLResearch.code4belgium.general.domain.Sector;
import be.PXLResearch.code4belgium.general.repository.SectorRepository;
import be.PXLResearch.code4belgium.schools.DTO.SchoolOrganizationDTO.SchoolOrganizationRequest;
import be.PXLResearch.code4belgium.schools.DTO.SchoolOrganizationDTO.SchoolOrganizationResponse;
import be.PXLResearch.code4belgium.schools.domain.School;
import be.PXLResearch.code4belgium.schools.domain.SchoolOrganization;
import be.PXLResearch.code4belgium.schools.repository.SchoolOrganizationRepository;
import be.PXLResearch.code4belgium.schools.repository.SchoolRepository;
import be.PXLResearch.code4belgium.schools.service.interfaces.ISchoolOrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SchoolOrganizationService implements ISchoolOrganizationService {
    private final SchoolOrganizationRepository schoolOrganizationRepository;
    private final SectorRepository sectorRepository;
    private final SchoolRepository schoolRepository;

    @Override
    public List<SchoolOrganizationResponse> getAllSchoolOrganizations() {
        List<SchoolOrganization> schoolOrganizations = schoolOrganizationRepository.findAll();

        if (schoolOrganizations.isEmpty()) {
            return List.of();
        }

        return schoolOrganizations.stream()
                .map(this::turnSchoolOrganizationToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public SchoolOrganizationResponse getSchoolOrganizationById(Long id) {
        SchoolOrganization schoolOrganization = schoolOrganizationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No school organization found with ID " + id));

        return turnSchoolOrganizationToResponse(schoolOrganization);
    }

    @Override
    public SchoolOrganization createSchoolOrganization(SchoolOrganizationRequest request) {
        List<School> schools = schoolRepository.findAll();
        SchoolOrganization parentOrganization = null;

        Sector sector = sectorRepository.findById(request.getSectorId()).orElseThrow(() -> new ResourceNotFoundException("No sector found with ID " + request.getSectorId()));

        if (request.getParentOrganizationId() != null) {
            parentOrganization = schoolOrganizationRepository.findById(request.getParentOrganizationId()).orElseThrow(() -> new ResourceNotFoundException("No organization found with id " + request.getParentOrganizationId()));
        }

        SchoolOrganization schoolOrganization = SchoolOrganization.builder()
                .name(request.getName())
                .parentOrganization(parentOrganization)
                .childOrganizations(request.getChildrenOrganizations().stream()
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList()))
                .branches(schools)
                .sector(sector)
                .type(request.getType())
                .address(request.getAddress())
                .postalCode(request.getPostalCode())
                .city(City.fromString(request.getCity()))
                .build();

        if (!schools.isEmpty()) {
            for (School school : schools) {
                school.setOrganization(schoolOrganization);
                schoolRepository.save(school);
            }
        }

        schoolOrganizationRepository.save(schoolOrganization);

        sector.getOrganizations().add(schoolOrganization);
        sectorRepository.save(sector);

        return schoolOrganization;
    }

    // Turns SchoolOrganization object into SchoolOrganizationResponse object
    private SchoolOrganizationResponse turnSchoolOrganizationToResponse(SchoolOrganization organization) {
        List<Organization<School>> orgs = organization.getChildOrganizations();

        List<SchoolOrganization> childOrgs = orgs.stream()
                .filter(SchoolOrganization.class::isInstance) // ensures type safety
                .map(SchoolOrganization.class::cast)
                .collect(Collectors.toList());

        return SchoolOrganizationResponse.builder()
                .id(organization.getId())
                .name(organization.getName())
                .parentOrganizationId(
                        organization.getParentOrganization() != null
                                ? organization.getParentOrganization().getId()
                                : null)
                .sectorId(organization.getSector().getId())
                .childrenOrganizations(childOrgs)
                .branches(organization.getBranches())
                .type(organization.getType())
                .address(organization.getAddress())
                .city(organization.getCity())
                .build();
    }
}
