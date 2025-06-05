package be.PXLResearch.code4belgium.general.service.interfaces;

import be.PXLResearch.code4belgium.general.DTO.FilterDTO.FilterRequest;
import be.PXLResearch.code4belgium.general.DTO.FilterDTO.FilterResponse;
import be.PXLResearch.code4belgium.general.domain.Filter;

import java.util.List;

public interface IFilterService {
    List<FilterResponse> getAllFilters();
    FilterResponse getFilterById(Long filterId);
    Filter createFilter(FilterRequest filterRequest);
    Filter editFilter(Long filterId, FilterRequest filterRequest);
}
