package be.PXLResearch.code4belgium.schools.service;

import be.PXLResearch.code4belgium.enums.City;
import be.PXLResearch.code4belgium.exceptions.ResourceNotFoundException;
import be.PXLResearch.code4belgium.schools.DTO.SchoolDTO.SchoolRequest;
import be.PXLResearch.code4belgium.schools.DTO.SchoolDTO.SchoolResponse;
import be.PXLResearch.code4belgium.schools.domain.School;
import be.PXLResearch.code4belgium.schools.domain.SchoolOrganization;
import be.PXLResearch.code4belgium.schools.repository.SchoolOrganizationRepository;
import be.PXLResearch.code4belgium.schools.repository.SchoolRepository;
import be.PXLResearch.code4belgium.schools.service.interfaces.ISchoolService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SchoolService implements ISchoolService {
    private final SchoolRepository schoolRepository;
    private final SchoolOrganizationRepository schoolOrganizationRepository;


    @Override
    public List<SchoolResponse> getAllSchools() {
        List<School> schools = schoolRepository.findAll();

        if (schools.isEmpty()) {
            return List.of();
        }

        return schools.stream()
                .map(e -> SchoolResponse.builder()
                        .id(e.getId())
                        .name(e.getName())
                        .address(e.getAddress())
                        .postalCode(e.getPostalCode())
                        .city(e.getCity())
                        .email(e.getEmail())
                        .phoneNumber(e.getPhoneNumber())
                        .website(e.getWebsite())
                        .rooms(e.getRooms())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public SchoolResponse getSchoolById(Long id) {
        School school = schoolRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No school found with id " + id));

        return SchoolResponse.builder()
                .id(school.getId())
                .name(school.getName())
                .address(school.getAddress())
                .postalCode(school.getPostalCode())
                .city(school.getCity())
                .email(school.getEmail())
                .phoneNumber(school.getPhoneNumber())
                .website(school.getWebsite())
                .build();
    }

    @Override
    public School createSchool(SchoolRequest request) throws IOException {
        SchoolOrganization organization = schoolOrganizationRepository.findById(request.getOrganizationId()).orElseThrow(() -> new ResourceNotFoundException("No organization found with id " + request.getOrganizationId()));

        School school = School.builder()
                .name(request.getName())
                .description(request.getDescription())
                .address(request.getAddress())
                .postalCode(request.getPostalCode())
                .city(City.fromString(request.getCity()))
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .website(request.getWebsite())
                .build();

        schoolRepository.save(school);

        organization.getBranches().add(school);
        schoolOrganizationRepository.save(organization);

        return school;
    }
}
