package be.PXLResearch.code4belgium.general.repository;

import be.PXLResearch.code4belgium.general.Filter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilterRepository extends JpaRepository<Filter, Long> {
}
