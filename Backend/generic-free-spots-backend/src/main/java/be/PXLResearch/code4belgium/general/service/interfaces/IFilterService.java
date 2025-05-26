package be.PXLResearch.code4belgium.general.service.interfaces;

import be.PXLResearch.code4belgium.general.DTO.Filter.FilterRequest;
import be.PXLResearch.code4belgium.general.DTO.Filter.FilterResponse;
import be.PXLResearch.code4belgium.general.Filter;

import java.util.List;

public interface IFilterService {
    List<FilterResponse> getAllFilters();
    FilterResponse getFilterById(Long filterId);
    Filter createFilter(FilterRequest filterRequest);
}
