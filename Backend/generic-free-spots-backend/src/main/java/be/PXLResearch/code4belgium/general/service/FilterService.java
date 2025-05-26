package be.PXLResearch.code4belgium.general.service;

import be.PXLResearch.code4belgium.enums.DataType;
import be.PXLResearch.code4belgium.exceptions.ResourceNotFoundException;
import be.PXLResearch.code4belgium.general.DTO.Filter.FilterRequest;
import be.PXLResearch.code4belgium.general.DTO.Filter.FilterResponse;
import be.PXLResearch.code4belgium.general.Filter;
import be.PXLResearch.code4belgium.general.Sector;
import be.PXLResearch.code4belgium.general.repository.FilterRepository;
import be.PXLResearch.code4belgium.general.service.interfaces.IFilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FilterService implements IFilterService {
    private final FilterRepository filterRepository;

    @Override
    public List<FilterResponse> getAllFilters() {
        List<Filter> filters = filterRepository.findAll();

        if (filters.isEmpty()) {
            return List.of();
        }

        return filters.stream()
                .map(f -> new FilterResponse(
                        f.getId(),
                        f.getName(),
                        f.getDatatype(),
                        f.getValue(),
                        f.getSectors(),
                        f.isActive(),
                        f.getInputType()
                ))
                .collect(Collectors.toList());

    }

    @Override
    public FilterResponse getFilterById(Long filterId) {
        Filter filter = filterRepository.findById(filterId).orElseThrow(() -> new ResourceNotFoundException("Could not find filter with ID " + filterId));

        return FilterResponse.builder()
                .id(filter.getId())
                .name(filter.getName())
                .datatype(filter.getDatatype())
                .value(filter.getValue())
                .sectors(filter.getSectors())
                .active(filter.isActive())
                .inputType(filter.getInputType())
                .build();
    }

    @Override
    public Filter createFilter(FilterRequest filterRequest) {
        List<Sector> sectors = new ArrayList<>();

        Filter filter = Filter.builder()
                .name(filterRequest.getName())
                .datatype(DataType.fromString(filterRequest.getDatatype()))
                .value("default")
                .sectors(sectors)
                .active(true)
                .inputType("Textbox")
                .build();

        return filterRepository.save(filter);
    }

    @Override
    public Filter editFilter(Long filterId, FilterRequest filterRequest) {
        Filter filter = filterRepository.findById(filterId).orElseThrow(() -> new ResourceNotFoundException("Could not find filter with ID " + filterId));

        filter.setValue(filterRequest.getValue());
        filter.setActive(filterRequest.isActive());
        filter.setInputType(filterRequest.getInputType());

        return filterRepository.save(filter);
    }
}
